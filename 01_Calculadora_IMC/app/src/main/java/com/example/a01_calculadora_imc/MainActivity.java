package com.example.a01_calculadora_imc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightEditText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = findViewById (R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        double weight = Double.parseDouble(weightEditText.getText().toString());
        double height = Double.parseDouble(heightEditText.getText().toString());
        double bmi = weight / (height * height);

        String bmiClassification;
        if (bmi < 18.5) {
            bmiClassification = "Underweight";
        } else if (bmi < 25) {
            bmiClassification = "Normal weight";
        } else if (bmi < 30) {
            bmiClassification = "Overweight";
        } else {
            bmiClassification = "Obese";
        }

        String result = "Your BMI is " + String.format("%.2f", bmi) + "\nClassification: " + bmiClassification;
        resultTextView.setText(result);
    }
}
