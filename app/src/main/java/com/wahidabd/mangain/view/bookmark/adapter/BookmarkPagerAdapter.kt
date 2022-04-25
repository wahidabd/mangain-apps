package com.wahidabd.mangain.view.bookmark.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wahidabd.mangain.view.bookmark.FavoriteFragment
import com.wahidabd.mangain.view.bookmark.HistoryFragment

class BookmarkPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when(position){
            0 -> FavoriteFragment()
            else -> HistoryFragment()
        }
}