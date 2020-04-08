package com.example.tipcalculatorproject_slominski_march15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText et_bill;
    TextView tv_tip, tv_people, tv_tip_output, tv_total_output;
    Button b_calculate, b_tip_minus, b_tip_plus, b_people_minus, b_people_plus;

    int tipPercent = 1;
    int totalPeople = 1;
    double billInit = 100.00;
    double tipOutput = 0;
    double totalOutput = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_bill = findViewById(R.id.et_bill);
        tv_tip = findViewById(R.id.tv_tip);
        tv_people = findViewById(R.id.tv_people);
        tv_tip_output = findViewById(R.id.tv_tip_output);
        tv_total_output = findViewById(R.id.tv_total_output);
        b_calculate = findViewById(R.id.b_calculate);
        b_tip_minus = findViewById(R.id.b_tip_minus);
        b_tip_plus = findViewById(R.id.b_tip_plus);
        b_people_minus = findViewById(R.id.b_people_minus);
        b_people_plus = findViewById(R.id.b_people_plus);

        b_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String billString = et_bill.getText().toString();
                if (!billString.equals("")) {
                    billInit = Double.valueOf(billString);
                    billInit = billInit * 100;
                    billInit = Math.round(billInit);
                    billInit = billInit / 100;

                    et_bill.setText(String.format(Locale.getDefault(), "%.2f", billInit));

                    tipOutput = (billInit * tipPercent) / 100;
                    tipOutput = tipOutput * 100;
                    tipOutput = Math.round(tipOutput);
                    tipOutput = tipOutput / 100;

                    if (totalPeople == 1) {
                        tv_tip_output.setText(String.format(Locale.getDefault(), "%.2f", tipOutput));

                        totalOutput = billInit + tipOutput;
                        tv_total_output.setText(String.format(Locale.getDefault(), "%.2f", totalOutput));
                    } else {

                        totalOutput = billInit + tipOutput;
                        totalOutput = totalOutput / totalPeople;
                        tv_total_output.setText(String.format(Locale.getDefault(), "%.2f", totalOutput) + " per person");

                        tipOutput = tipOutput / totalPeople;
                        tv_tip_output.setText(String.format(Locale.getDefault(), "%.2f", tipOutput) + " per person");

                    }
                }
            }
        });
        b_tip_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipPercent > 1) {
                    tipPercent--;
                    tv_tip.setText(tipPercent + "%");
                }

            }
        });
        b_tip_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent++;
                tv_tip.setText(tipPercent + "%");

            }
        });
        b_people_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalPeople > 1) {
                    totalPeople--;
                    tv_people.setText(totalPeople + "");
                }
            }
        });
        b_people_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPeople++;
                tv_people.setText(totalPeople + "");

            }
        });

    }
}
