package com.example.greenscreen.vehicles;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greenscreen.R;

import java.text.DecimalFormat;

public class Bus extends AppCompatActivity {
    private static DecimalFormat df = new DecimalFormat("0.00");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        TextView CO2 = (TextView) findViewById(R.id.text_view);
        TextView TIME = (TextView) findViewById(R.id.text_view2);
        Bundle bus = getIntent().getExtras();
        double result = bus.getDouble("key");
        double emission = 822*result/56;
        double time = result/20.9215;
        CO2.setText("CO2 emissions: "+df.format(emission)+"g/km");
        TIME.setText("Time Taken: " + df.format(time) + "hours");



    }
}
