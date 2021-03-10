@file:Suppress("DEPRECATION")

package com.example.greenscreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ekn.gruzer.gaugelibrary.FullGauge
import com.example.greenscreen.HalfGaugeFragment

class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return HalfGaugeFragment.newInstance()
        }
        return HalfGaugeFragment.newInstance()
    }

    override fun getCount(): Int {
        return 1
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "HalfGauge"
        }
        return "Title"
    }
}