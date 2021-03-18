package com.example.greenscreen.Scan;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.greenscreen.R;

public class Pop extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_window);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.9));
    }
}
