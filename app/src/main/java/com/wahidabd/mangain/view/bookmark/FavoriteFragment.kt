package com.wahidabd.mangain.view.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.wahidabd.mangain.R
import com.wahidabd.mangain.databinding.FragmentFavoriteBinding
import com.wahidabd.mangain.view.bookmark.adapter.BookmarkAdapter
import com.wahidabd.mangain.viewmodel.LocalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LocalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookmarkAdapter = BookmarkAdapter(requireContext())
        bookmarkAdapter.setOnItemClicked {
            val action = BookmarkFragmentDirections.actionBookmarkFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.rvBookmark.apply {
            adapter = bookmarkAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            itemAnimator = DefaultItemAnimator()
        }

        viewModel.allBookmark().observe(viewLifecycleOwner){
            bookmarkAdapter.setData = it
        }
    }



}