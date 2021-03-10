package com.example.greenscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.greenscreen.R
import kotlinx.android.synthetic.main.main_fragment.*

class yourGreenscore : AppCompatActivity() {



        companion object {
            fun newInstance() = yourGreenscore()
        }


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View {
            return inflater.inflate(R.layout.main_fragment, container, false)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val adapter = Adapter(childFragmentManager)
            pager.adapter = adapter
            sliding_tabs.setupWithViewPager(pager)

        }

    }

}