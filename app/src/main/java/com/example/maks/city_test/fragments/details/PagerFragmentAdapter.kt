package com.example.maks.city_test.fragments.details

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.maks.city_test.fragments.details.details_frag.DetailsFragment
import com.example.maks.city_test.fragments.details.map_frag.MapFragment

class PagerFragmentAdapter(val fm: FragmentManager, val bundle: Bundle) : FragmentPagerAdapter(fm) {

    var fragmentDetails:DetailsFragment=DetailsFragment()
        get() {
        val f=DetailsFragment()
            f.bundle=bundle
            return f
        }
    var list: ArrayList<Fragment> =
        arrayListOf(
            MapFragment()
            ,fragmentDetails)

    override fun getItem(p0: Int): Fragment = list[p0]

    override fun getCount(): Int = list.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }

}