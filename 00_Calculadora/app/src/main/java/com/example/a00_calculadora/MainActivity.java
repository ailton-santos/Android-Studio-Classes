package com.example.a00_calculadora;
import android.os.Bundle;
/* Bundle: é um formato de publicação que
inclui todos os
recursos e códigos compilados do seu app
e adia a geração e a assinatura do APK no Google Play
 */
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.a00_calculadora.R;

public class MainActivity extends AppCompatActivity {
    /*AppCompatActivity, é uma filha de Activity,
    possui todos os comportamentos da
    classe pai, contudo ela é uma classe que
    funciona nas versões mais antigas do Android,
    além da mais recente, é uma classe de suporte */
    EditText etNumber;
    EditText etNumber2;
    Button btnAdd;

    Button btnSub;

    Button btnMul;

    Button btnDiv;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*findViewById = é um método que permite
        localizar um widget (como um botão, por exemplo) através de um id
         */
        etNumber = findViewById(R.id.et_number);
        etNumber2 = findViewById(R.id.et_number2);
        btnAdd = findViewById(R.id.btn_add);
        btnSub = findViewById(R.id.btn_sub);
        btnMul = findViewById(R.id.btn_mul);
        btnDiv = findViewById(R.id.btn_div);
        tvResult = findViewById(R.id.tv_result);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(etNumber.getText().toString());
                int number2 = Integer.parseInt(etNumber2.getText().toString());
                int result = number + number2;
                tvResult.setText(String.valueOf(result));
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(etNumber.getText().toString());
                int number2 = Integer.parseInt(etNumber2.getText().toString());
                int result = number - number2;
                tvResult.setText(String.valueOf(result));
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(etNumber.getText().toString());
                int number2 = Integer.parseInt(etNumber2.getText().toString());
                int result = number * number2;
                tvResult.setText(String.valueOf(result));
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(etNumber.getText().toString());
                int number2 = Integer.parseInt(etNumber2.getText().toString());
                int result = number / number2;
                tvResult.setText(String.valueOf(result));
            }
        });
    }
}

