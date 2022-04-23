package com.wahidabd.mangain.view.manga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wahidabd.mangain.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MangaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manga, container, false)
    }

}