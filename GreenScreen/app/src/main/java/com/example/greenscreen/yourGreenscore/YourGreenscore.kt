package com.example.greenscreen.yourGreenscore

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ekn.gruzer.gaugelibrary.Range
import com.example.greenscreen.R
import kotlinx.android.synthetic.main.activity_your_greenscore.*

class yourGreenscore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_greenscore)

        val range = Range()
        range.color = Color.parseColor("#ce0000")
        range.from =0.0
        range.to = 33.0

        val range2 = Range()
        range2.color = Color.parseColor("#E3E500")
        range2.from = 33.0
        range2.to = 66.0

        val range3 = Range()
        range3.color = Color.parseColor("#00b20b")
        range3.from = 66.0
        range3.to = 100.0

        //add color ranges to gauge
        //add color ranges to gauge
        halfGauge.addRange(range)
        halfGauge.addRange(range2)
        halfGauge.addRange(range3)

        //set min max and current value
        //set min max and current value
        halfGauge.minValue = 0.0
        halfGauge.maxValue = (100).toDouble()
        halfGauge.value = (50).toDouble()

        halfGauge.setNeedleColor(Color.DKGRAY)
        halfGauge.valueColor = Color.BLUE
        halfGauge.minValueTextColor = Color.RED
        halfGauge.maxValueTextColor = Color.GREEN




        half_gauge_update_btn.setOnClickListener {
            halfGauge.value = half_gauge_value_ed.text.toString().toDouble()
        }

    }
}



