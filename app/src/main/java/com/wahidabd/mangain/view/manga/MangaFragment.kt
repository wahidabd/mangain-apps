package com.wahidabd.mangain.view.manga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.wahidabd.mangain.R
import com.wahidabd.mangain.databinding.FragmentMangaBinding
import com.wahidabd.mangain.view.home.adapter.KomikPagingAdapter
import com.wahidabd.mangain.view.manga.adapter.KomikPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MangaFragment : Fragment() {

    private var _binding: FragmentMangaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMangaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = KomikPagerAdapter(requireParentFragment())
        binding.viewPager.adapter = pagerAdapter

        val tab = listOf("Komik", "Manhwa", "Manhua")
        TabLayoutMediator(binding.tabLayout, binding.viewPager){t, p ->
            t.text = tab[p]
        }.attach()
    }

}