package com.example.greenscreen.vehicles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greenscreen.R;

import java.text.DecimalFormat;

public class Car extends AppCompatActivity {

    EditText emissions;
    Button button;
    TextView CO2,overallCO2;
    double value=0;
    String text = " ";
    private static DecimalFormat df = new DecimalFormat("0.00");

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        button=findViewById(R.id.result);
        emissions = findViewById(R.id.CO2);
        CO2 = (TextView) findViewById(R.id.text_view);
        overallCO2 = (TextView) findViewById(R.id.overall);
        Bundle car = getIntent().getExtras();
        double result = car.getDouble("key");


       /* String text = emissions.getText().toString();

        if(!text.isEmpty()){
                button.setEnabled(true);

        }
*/
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                text = emissions.getText().toString();
                value= Double.parseDouble(text);
                double something = result*value;
                ((TextView) Car.this.findViewById(R.id.overall)).setText("CO2 emissions: "+df.format(something)+" g/km");

            }
        });
                // it means it is double

        CO2.setText("Car's CO2 emissions: ");


    }
}
