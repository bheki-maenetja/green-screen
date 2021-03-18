package com.example.greenscreen

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.*
import com.example.greenscreen.Scan.ScanBarcode
import com.example.greenscreen.yourGreenscore.yourGreenscore
import com.google.zxing.integration.android.IntentIntegrator

class AfterLogin : AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_login2)
        val Greenscore = findViewById<Button>(R.id.Greenscore)
        Greenscore.setOnClickListener{
            val intent = Intent(this@AfterLogin, yourGreenscore::class.java)
            startActivity(intent)
        }

        val btn_scan = findViewById<Button>(R.id.btn_scan)
        //scan button
        btn_scan.setOnClickListener {

            val intent = Intent(this@AfterLogin, ScanBarcode::class.java)
            startActivity(intent)
           /* val scanner = IntentIntegrator(this)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()*/
        }

        val plan_your_travel = findViewById<Button>(R.id.plan_your_travel)
        plan_your_travel.setOnClickListener {
            val intent = Intent(this@AfterLogin,MainActivityTravel::class.java)
            startActivity(intent)
        }
    }


// scan
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
                }
    }

}
}