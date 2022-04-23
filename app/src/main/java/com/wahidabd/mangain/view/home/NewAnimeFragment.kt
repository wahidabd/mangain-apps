package com.wahidabd.mangain.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wahidabd.mangain.databinding.FragmentNewAnimeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewAnimeFragment : Fragment() {

    private var _binding: FragmentNewAnimeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

}