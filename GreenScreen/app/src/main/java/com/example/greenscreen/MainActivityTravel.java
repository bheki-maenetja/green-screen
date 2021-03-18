package com.example.greenscreen;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.greenscreen.vehicles.Bus;
import com.example.greenscreen.vehicles.Car;
import com.example.greenscreen.vehicles.Cycle;
import com.example.greenscreen.vehicles.Plane;
import com.example.greenscreen.vehicles.Train;
import com.example.greenscreen.vehicles.Walk;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivityTravel extends AppCompatActivity implements View.OnClickListener {

    //Todo: Enable Buttons when in KM range
    //Todo: CO2 Emissions for every vehicle
    //Todo: Embed Gauge in every vehicle class

    EditText etSource,etDestination;
    TextView textView;
    String sType;
    double lat1 = 0,long1 =0,lat2=0,long2=0;
    int flag = 0;
    double distance = 0.0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_your_travel);

        //declared buttons

        Button train = (Button) findViewById(R.id.train);
        Button car = (Button) findViewById(R.id.car);
        Button walk = (Button) findViewById(R.id.walk);
        Button cycle = (Button) findViewById(R.id.cycle);
        Button plane = (Button) findViewById(R.id.plane);
        Button bus = (Button) findViewById(R.id.bus);
        train.setOnClickListener(this);
        car.setOnClickListener(this);
        walk.setOnClickListener(this);
        cycle.setOnClickListener(this);
        plane.setOnClickListener(this);
        bus.setOnClickListener(this);



        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        textView = findViewById(R.id.text_view);
        //Initialize places
        Places.initialize(getApplication(), "AIzaSyCNvmfYxCX95iJpleZUcIP8WLcXcDRa4vY");
        etSource.setFocusable(false);

        //Set edit text non focusable
        etSource.setFocusable(false);
        etSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Define type
                sType = "source";
                //Initialize place field list
                List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS
                        , Place.Field.LAT_LNG);

                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields
                ).build(MainActivityTravel.this);
                //Start activity result
                startActivityForResult(intent, 100);
            }
        });
        //Set edit text non focusable
        etDestination.setFocusable(false);
        etDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sType = "destination";
                //Initialize place field list
                List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS
                        , Place.Field.LAT_LNG);
                //Create intent
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields
                ).build(MainActivityTravel.this);
                //Start activity result
                startActivityForResult(intent, 100);
            }
        });
        textView.setText("0.0 Kilometers");
        //Set text on text view


    }


     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
         super.onActivityResult(requestCode,resultCode,data);
         //Check condition
         try {
             if (resultCode == RESULT_OK && requestCode == 100) {
                 //When succes
                 //Initialize place
                 Place place = Autocomplete.getPlaceFromIntent(data);
                 //Check condition
                 if (sType.equals("source")) {
                     //When type is source
                     //Increase flag value
                     flag++;
                     //Set adress on edit text
                     etSource.setText(place.getAddress());
                     //Get latitude and longitude
                     String sSource = String.valueOf(place.getLatLng());
                     sSource = sSource.replaceAll("lat/lng:", "");
                     sSource = sSource.replace("(", "");
                     sSource = sSource.replace(")", "");
                     String[] split = sSource.split(",");
                     lat1 = Double.parseDouble(split[0]);
                     long1 = Double.parseDouble(split[1]);
                 } else {
                     //When type is destination
                     //Increase flag value
                     flag++;
                     //Set adress on edit text
                     etDestination.setText(place.getAddress());
                     //Get latitude and longitude
                     String sDestination = String.valueOf(place.getLatLng());
                     sDestination = sDestination.replaceAll("lat/lng: ", "");
                     sDestination = sDestination.replace("(", "");
                     sDestination = sDestination.replace(")", "");
                     String[] split = sDestination.split(",");
                     lat2 = Double.parseDouble(split[0]);
                     long2 = Double.parseDouble(split[1]);
                 }
                 if (flag >= 2) {
                     //When flag is greater than and equal to 2
                     //Calculate distance
                     distance(lat1, long1, lat2, long2);
                 }

             } else if (requestCode == AutocompleteActivity.RESULT_ERROR) {
                 Status status = Autocomplete.getStatusFromIntent(data);
                 Toast.makeText(getApplicationContext(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();
             }
         }catch(NumberFormatException X)
         {
             System.out.println("something");
         }

        }


    private void distance(double lat1, double long1, double lat2, double long2) {
        //Calculate longitude difference
        double longDiff = long1 - long2;
        //Calculate distance
        double distance = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(longDiff));
         distance= Math.acos(distance);
         distance = rad2deg(distance);
         distance = distance * 60 * 1.1515;
         distance = distance * 1.609344;
         this.distance = distance;
         textView.setText(String.format(Locale.US,"%2f Kilometer",distance));


        Button train = (Button) findViewById(R.id.train);
        Button car = (Button) findViewById(R.id.car);
        Button walk = (Button) findViewById(R.id.walk);
        Button cycle = (Button) findViewById(R.id.cycle);
        Button plane = (Button) findViewById(R.id.plane);
        Button bus = (Button) findViewById(R.id.bus);

        if(distance != 0.0) {
            if (distance > 0 && distance < 15) { //0-15
                walk.setEnabled(true);
                cycle.setEnabled(true);
                plane.setEnabled(false);
                if(distance> 1)
                {
                    bus.setEnabled(true);
                    car.setEnabled(true);
                }
            }
            else if (distance > 0 && distance < 25) { //0-25
                walk.setEnabled(false);
                cycle.setEnabled(true);
                if(distance> 15)
                {
                    bus.setEnabled(true);
                    car.setEnabled(true);
                    train.setEnabled(true);
                }
            }
            else if (distance > 25 && distance < 400) {
                walk.setEnabled(false);
                cycle.setEnabled(false);
                bus.setEnabled(true);
                car.setEnabled(true);
                train.setEnabled(true);
                plane.setEnabled(false);
                if(distance>150)
                    plane.setEnabled(true);

            }
            else if (distance > 50 && distance < 600) { //0-150
                walk.setEnabled(false);
                cycle.setEnabled(false);
                bus.setEnabled(false);
                train.setEnabled(true);
                car.setEnabled(true);
                if(distance>150)
                    plane.setEnabled(false);
            }
            else if (distance > 150) { //150+
                walk.setEnabled(false);
                cycle.setEnabled(false);
                bus.setEnabled(false);
                train.setEnabled(false);
                plane.setEnabled(true);
            }

        }


    }

    private double rad2deg(double distance){
        return (distance * 180.0/Math.PI);
    }
    //Conver degree to radian
    private double deg2rad(double lat1) {
        return (lat1*Math.PI/180.0);
    }



    //onClick switch

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.train:
                Intent intent1 = new Intent(this, Train.class);
                Bundle train = new Bundle();
                train.putDouble("key", this.distance);
                intent1.putExtras(train);
                startActivity(intent1);
                break;
            case R.id.car:
                Intent intent2 = new Intent(this, Car.class);
                Bundle car = new Bundle();
                car.putDouble("key", this.distance);
                intent2.putExtras(car);
                startActivity(intent2);
                break;
            case R.id.cycle:
                Intent intent3 = new Intent(this, Cycle.class);
                Bundle cycle = new Bundle();
                cycle.putDouble("key", this.distance);
                intent3.putExtras(cycle);
                startActivity(intent3);
                break;
            case R.id.walk:
                Intent intent4 = new Intent(this, Walk.class);
                Bundle walk = new Bundle();
                walk.putDouble("key", this.distance);
                intent4.putExtras(walk);
                startActivity(intent4);
                break;
            case R.id.plane:
                Intent intent5 = new Intent(this, Plane.class);
                Bundle plane = new Bundle();
                plane.putDouble("key", this.distance);
                intent5.putExtras(plane);
                startActivity(intent5);
                break;
            case R.id.bus:
                Intent intent6 = new Intent(this, Bus.class);
                Bundle bus = new Bundle();
                bus.putDouble("key", this.distance);
                intent6.putExtras(bus);
                startActivity(intent6);
                break;
            default:
                break;
        }
}
}


