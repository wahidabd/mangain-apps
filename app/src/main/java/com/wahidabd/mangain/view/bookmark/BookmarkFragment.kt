package com.wahidabd.mangain.view.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.wahidabd.mangain.R
import com.wahidabd.mangain.databinding.FragmentBookmarkBinding
import com.wahidabd.mangain.view.bookmark.adapter.BookmarkPagerAdapter
import com.wahidabd.mangain.view.manga.adapter.KomikPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        val pagerAdapter = BookmarkPagerAdapter(requireParentFragment())
        binding.viewPager.adapter = pagerAdapter

        val tab = listOf("Bookmark", "History")
        TabLayoutMediator(binding.tabLayout, binding.viewPager){t, p ->
            t.text = tab[p]
        }.attach()

        return binding.root
    }

}