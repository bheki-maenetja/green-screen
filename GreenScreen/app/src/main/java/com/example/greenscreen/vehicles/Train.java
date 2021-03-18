package com.example.greenscreen.vehicles;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greenscreen.R;

import java.text.DecimalFormat;

public class Train extends AppCompatActivity {

    private static DecimalFormat df = new DecimalFormat("0.00");


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        TextView CO2 = (TextView) findViewById(R.id.text_view);
        TextView TIME = (TextView) findViewById(R.id.text_view2);
        Bundle train = getIntent().getExtras();
        double result = train.getDouble("key");
        double emission = 35.1*result;
        double time = result/225.0;
        CO2.setText("CO2 emissions: "+df.format(emission)+"g/km");
        TIME.setText("Time Taken: " + df.format(time) + "hours");

    }
}
