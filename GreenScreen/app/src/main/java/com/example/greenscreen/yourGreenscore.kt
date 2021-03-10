package com.example.greenscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.greenscreen.MainFragment.*

class yourGreenscore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_greenscore)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }


}
