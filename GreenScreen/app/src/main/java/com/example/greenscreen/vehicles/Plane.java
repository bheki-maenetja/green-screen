package com.example.greenscreen.vehicles;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greenscreen.R;

import java.text.DecimalFormat;

public class Plane extends AppCompatActivity {

    private static DecimalFormat df = new DecimalFormat("0.00");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane);
        TextView CO2 = (TextView) findViewById(R.id.text_view);
        TextView TIME = (TextView) findViewById(R.id.text_view2);
        Bundle plane = getIntent().getExtras();
        double result = plane.getDouble("key");
        double emission = 115 * result;
        double time = result / 938.248;
        CO2.setText("CO2 emissions: " + df.format(emission) + "g/km");
        TIME.setText("Time Taken: " + df.format(time) + "hours");
    }
}
