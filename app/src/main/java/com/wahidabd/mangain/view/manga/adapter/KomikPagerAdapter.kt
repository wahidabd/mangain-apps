package com.wahidabd.mangain.view.manga.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wahidabd.mangain.view.manga.KomikFragment
import com.wahidabd.mangain.view.manga.ManhuaFragment
import com.wahidabd.mangain.view.manga.ManhwaFragment

class KomikPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        when(position){
            0 -> KomikFragment()
            1 -> ManhwaFragment()
            else -> ManhuaFragment()
        }
}