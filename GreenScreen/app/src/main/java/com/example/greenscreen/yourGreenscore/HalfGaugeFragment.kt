package com.example.greenscreen.yourGreenscore

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ekn.gruzer.gaugelibrary.Range
import com.example.greenscreen.R
import kotlinx.android.synthetic.main.activity_your_greenscore.*


class HalfGaugeFragment : Fragment() {

    companion object {
        fun newInstance() = HalfGaugeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_your_greenscore, container, false)
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val range = Range()
        range.color = Color.parseColor("#ce0000")
        range.from =0.0
        range.to = -50.0

        val range2 = Range()
        range2.color = Color.parseColor("#E3E500")
        range2.from = -50.0
        range2.to = -100.0

        val range3 = Range()
        range3.color = Color.parseColor("#00b20b")
        range3.from = -100.0
        range3.to = -150.0

        //add color ranges to gauge
        //add color ranges to gauge
        halfGauge.addRange(range)
        halfGauge.addRange(range2)
        halfGauge.addRange(range3)

        //set min max and current value
        //set min max and current value
        halfGauge.minValue = 0.0
        halfGauge.maxValue = (-150).toDouble()
        halfGauge.value = (-80).toDouble()

        halfGauge.setNeedleColor(Color.DKGRAY)
        halfGauge.valueColor = Color.BLUE
        halfGauge.minValueTextColor = Color.RED
        halfGauge.maxValueTextColor = Color.GREEN


        /*
         val range = Range()
         range.color = Color.parseColor("#ce0000")
         range.from = 0.0
         range.to = 50.0
         val range2 = Range()
         range2.color = Color.parseColor("#E3E500")
         range2.from = 50.0
         range2.to = 100.0
         val range3 = Range()
         range3.color = Color.parseColor("#00b20b")
         range3.from = 100.0
         range3.to = 150.0
         halfGauge.addRange(range)
         halfGauge.addRange(range2)
         halfGauge.addRange(range3)
         halfGauge.minValue = 0.0
         halfGauge.maxValue = 150.0
         halfGauge.value = 0.0*/


        half_gauge_update_btn.setOnClickListener {
            halfGauge.value = half_gauge_value_ed.text.toString().toDouble()
        }

    }


}