package com.example.greenscreen.yourGreenscore

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ekn.gruzer.gaugelibrary.Range
import com.example.greenscreen.R
import com.example.greenscreen.vehicles.*
import kotlinx.android.synthetic.main.activity_your_greenscore.*
import java.io.File
import java.time.LocalDateTime
import java.util.*


class yourGreenscore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_greenscore)

        val range = Range()
        range.color = Color.parseColor("#00b20b")
        range.from =0.0
        range.to = 33.0

        val range2 = Range()
        range2.color = Color.parseColor("#E3E500")
        range2.from = 33.0
        range2.to = 66.0

        val range3 = Range()
        range3.color = Color.parseColor("#ce0000")
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
        halfGauge.value = (0).toDouble()

        halfGauge.setNeedleColor(Color.DKGRAY)
        halfGauge.valueColor = Color.BLUE
        halfGauge.minValueTextColor = Color.GREEN
        halfGauge.maxValueTextColor = Color.RED

        val pm: PackageManager = this.getPackageManager()
        val appInfo = pm.getApplicationInfo("com.example.greenscreen", 0)
        val appFile = appInfo.sourceDir
        val installed: Long = File(appFile).lastModified()

        val diff: Long = Date().getTime() - installed
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = (hours / 24) +1

        halfGauge.value = (halfGauge.value + Car.greenScore + Train.greenScore + Cycle.greenScore + Bus.greenScore + Walk.greenScore + Plane.greenScore)/days



    }
}



