package com.example.greenscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import java.util.*

class PlanYourTravel : AppCompatActivity() {
    var etSource: EditText? = null
    var etDestination: EditText? = null
    var textView: TextView? = null
    var sType: String? = null
    var lat1 = 0.0
    var long1 = 0.0
    var lat2 = 0.0
    var long2 = 0.0
    var flag = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_your_travel)
        etSource = findViewById(R.id.et_source)
        etDestination = findViewById(R.id.et_destination)
        textView = findViewById(R.id.text_view)
        //Initialize places
        Places.initialize(application, "AIzaSyCNvmfYxCX95iJpleZUcIP8WLcXcDRa4vY")
        etSource?.setFocusable(false)

        //Set edit text non focusable
        etSource?.setFocusable(false)
        etSource?.setOnClickListener(View.OnClickListener { //Define type
            sType = "source"
            //Initialize place field list
            val fields = Arrays.asList(
                Place.Field.ADDRESS, Place.Field.LAT_LNG
            )
            val intent = Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY, fields
            ).build(this@PlanYourTravel)
            //Start activity result
            startActivityForResult(intent, 100)
        })
        //Set edit text non focusable
        etDestination?.setFocusable(false)
        etDestination?.setOnClickListener(View.OnClickListener {
            sType = "destination"
            //Initialize place field list
            val fields = Arrays.asList(
                Place.Field.ADDRESS, Place.Field.LAT_LNG
            )
            //Create intent
            val intent = Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY, fields
            ).build(this@PlanYourTravel)
            //Start activity result
            startActivityForResult(intent, 100)
          /* registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                onActivityResult(100, result)
            }.launch(intent) <---- alternative method to startActivityForResult (it doesn't work)*/
        })
        //Set text on text view
        textView?.setText("0.0 Kilometers")
    }


    override fun onActivityResult(requestCode: Int,resultCode: Int,data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Check condition
        if (requestCode == 100 ) {
            val intent = data
            //When succes
            //Initialize place
            val place = Autocomplete.getPlaceFromIntent(intent!!)
            //Check condition
            if (sType == "source") {
                //When type is source
                //Increase flag value
                flag++
                //Set adress on edit text
                etSource!!.setText(place.address)
                //Get latitude and longitude
                var sSource = place.latLng.toString()
                sSource = sSource.replace("lat/lng:".toRegex(), "")
                sSource = sSource.replace("(", "")
                sSource = sSource.replace(")", "")
                val split = sSource.split(",".toRegex()).toTypedArray()
                lat1 = split[0].toDouble()
                long1 = split[1].toDouble()
            } else {
                //When type is destination
                //Increase flag value
                flag++
                //Set adress on edit text
                etDestination!!.setText(place.address)
                //Get latitude and longitude
                var sDestination = place.latLng.toString()
                sDestination = sDestination.replace("lat/lng:".toRegex(), "")
                sDestination = sDestination.replace("(", "")
                sDestination = sDestination.replace(")", "")
                val split = sDestination.split(",".toRegex()).toTypedArray()
                lat2 = split[0].toDouble()
                long2 = split[1].toDouble()
            }
            if (flag >= 2) {
                //When flag is greater than and equal to 2
                //Calculate distance
                distance(lat1, long1, lat2, long2)
            } else if (requestCode == AutocompleteActivity.RESULT_ERROR) {
                val status = Autocomplete.getStatusFromIntent(
                    data
                )
                Toast.makeText(applicationContext, status.statusMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun distance(lat1: Double, long1: Double, lat2: Double, long2: Double) {
        //Calculate longitude difference
        val longDiff = long1 - long2
        //Calculate distance
        var distance = Math.sin(deg2rad(lat1))
            Math.sin(deg2rad(lat2))
            Math.cos(deg2rad(lat1))
            Math.cos(deg2rad(lat2))
            Math.cos(deg2rad(longDiff))
        distance = Math.acos(distance)
        distance = rad2deg(distance)
        distance = distance * 60 * 1.1515
        distance = distance * 1.609344
        textView!!.text = String.format(Locale.US, "%2f Kilometer", distance)
    }

    private fun rad2deg(distance: Double): Double {
        return distance * 180.0 / Math.PI
    }

    //Conver degree to radian
    private fun deg2rad(lat1: Double): Double {
        return lat1 * Math.PI / 180.0
    }
}