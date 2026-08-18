package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5,
            button6, button7, button8, button9;

    Button buttonC, button10, buttonAdd, buttonSub,
            buttonMul, buttonDiv, buttonEql;

    EditText result;

    float mValueOne = 0;
    float mValueTwo = 0;

    boolean addition = false;
    boolean subtract = false;
    boolean multiplication = false;
    boolean division = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Display
        result = findViewById(R.id.edt1);

        // Number buttons
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        // Other buttons
        button10 = findViewById(R.id.button10);
        buttonC = findViewById(R.id.buttonC);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSub = findViewById(R.id.buttonSub);
        buttonMul = findViewById(R.id.buttonMul);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonEql = findViewById(R.id.buttonEql);

        // Number button listeners
        button0.setOnClickListener(v -> appendNumber("0"));
        button1.setOnClickListener(v -> appendNumber("1"));
        button2.setOnClickListener(v -> appendNumber("2"));
        button3.setOnClickListener(v -> appendNumber("3"));
        button4.setOnClickListener(v -> appendNumber("4"));
        button5.setOnClickListener(v -> appendNumber("5"));
        button6.setOnClickListener(v -> appendNumber("6"));
        button7.setOnClickListener(v -> appendNumber("7"));
        button8.setOnClickListener(v -> appendNumber("8"));
        button9.setOnClickListener(v -> appendNumber("9"));

        // Decimal button
        button10.setOnClickListener(v -> {

            String currentText = result.getText().toString();

            if (currentText.isEmpty()) {
                result.setText("0.");
            } else if (!currentText.contains(".")) {
                result.append(".");
            }
        });

        // Addition
        buttonAdd.setOnClickListener(v -> setOperation("add"));

        // Subtraction
        buttonSub.setOnClickListener(v -> setOperation("subtract"));

        // Multiplication
        buttonMul.setOnClickListener(v -> setOperation("multiply"));

        // Division
        buttonDiv.setOnClickListener(v -> setOperation("divide"));

        // Clear
        buttonC.setOnClickListener(v -> clearCalculator());

        // Equal
        buttonEql.setOnClickListener(v -> calculateResult());
    }

    // Add a number to the display
    private void appendNumber(String number) {
        result.append(number);
    }

    // Store the first value and selected operation
    private void setOperation(String operation) {

        String currentText = result.getText().toString();

        if (currentText.isEmpty()) {
            return;
        }

        mValueOne = Float.parseFloat(currentText);

        // Reset all operations
        addition = false;
        subtract = false;
        multiplication = false;
        division = false;

        switch (operation) {

            case "add":
                addition = true;
                break;

            case "subtract":
                subtract = true;
                break;

            case "multiply":
                multiplication = true;
                break;

            case "divide":
                division = true;
                break;
        }

        result.setText("");
    }

    // Calculate the result
    private void calculateResult() {

        String currentText = result.getText().toString();

        if (currentText.isEmpty()) {
            return;
        }

        mValueTwo = Float.parseFloat(currentText);

        float answer = 0;

        if (addition) {

            answer = mValueOne + mValueTwo;
            addition = false;

        } else if (subtract) {

            answer = mValueOne - mValueTwo;
            subtract = false;

        } else if (multiplication) {

            answer = mValueOne * mValueTwo;
            multiplication = false;

        } else if (division) {

            if (mValueTwo == 0) {
                result.setText("Cannot divide by 0");
                division = false;
                return;
            }

            answer = mValueOne / mValueTwo;
            division = false;

        } else {
            return;
        }

        result.setText(String.valueOf(answer));
    }

    // Clear calculator
    private void clearCalculator() {

        result.setText("");

        mValueOne = 0;
        mValueTwo = 0;

        addition = false;
        subtract = false;
        multiplication = false;
        division = false;
    }
}
