package com.example.greenscreen.yourGreenscore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.greenscreen.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {



    companion object {
        fun newInstance() = MainFragment()
    }


      override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                      savedInstanceState: Bundle?): View? {
         return inflater.inflate(R.layout.main_fragment, container, false)
     }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = Adapter(childFragmentManager)
        pager.adapter = adapter
        sliding_tabs.setupWithViewPager(pager)
        }



}