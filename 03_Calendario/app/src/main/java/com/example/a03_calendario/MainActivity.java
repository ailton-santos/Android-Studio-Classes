package com.example.a03_calendario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView dateTextView;
    private EditText eventEditText;
    private Button addEventButton;
    private TextView eventsTextView;

    private HashMap<String, List<String>> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        dateTextView = findViewById(R.id.dateTextView);
        eventEditText = findViewById(R.id.eventEditText);
        addEventButton = findViewById(R.id.addEventButton);
        eventsTextView = findViewById(R.id.eventsTextView);

        events = new HashMap<>();

        // Set the initial date to today's date
        long currentDate = System.currentTimeMillis();
        dateTextView.setText(getDate(currentDate));

        // Set the listener for the calendar view
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Update the date text view to show the selected date
                long selectedDate = getDateInMillis(year, month, dayOfMonth);
                dateTextView.setText(getDate(selectedDate));

                // Show the events for the selected date
                showEventsForDate(getDate(selectedDate));
            }
        });

        // Set the listener for the add event button
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String event = eventEditText.getText().toString().trim();
                if (event.isEmpty()) {
                    return;
                }
                String date = dateTextView.getText().toString();
                List<String> eventList = events.get(date);
                if (eventList == null) {
                    eventList = new ArrayList<>();
                    events.put(date, eventList);
                }
                eventList.add(event);
                eventEditText.setText("");
                showEventsForDate(date);
            }
        });
    }

    private long getDateInMillis(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        return calendar.getTimeInMillis();
    }

    private String getDate(long millis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(new Date(millis));
    }

    private void showEventsForDate(String date) {
        List<String> eventList = events.get(date);
        if (eventList == null) {
            eventsTextView.setText("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String event : eventList) {
            sb.append(event).append("\n");
        }
        eventsTextView.setText(sb.toString());
    }
}
