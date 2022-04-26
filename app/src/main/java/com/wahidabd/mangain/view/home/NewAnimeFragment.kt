package com.wahidabd.mangain.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidabd.mangain.databinding.FragmentNewAnimeBinding
import com.wahidabd.mangain.view.home.adapter.NewUpdatePagingAdapter
import com.wahidabd.mangain.view.manga.adapter.KomikPagingAdapter
import com.wahidabd.mangain.view.manga.adapter.KomikLoadStateAdapter
import com.wahidabd.mangain.viewmodel.KomikViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NewAnimeFragment : Fragment() {

    private var _binding: FragmentNewAnimeBinding? = null
    private val binding get() = _binding!!

    private lateinit var pagingAdapter: NewUpdatePagingAdapter
    private val viewModel: KomikViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener { findNavController().navigateUp() }

        pagingAdapter = NewUpdatePagingAdapter(requireContext())
        binding.rvKomik.apply {
            adapter = pagingAdapter.withLoadStateHeaderAndFooter(
                header = KomikLoadStateAdapter{pagingAdapter.retry()},
                footer = KomikLoadStateAdapter{pagingAdapter.retry()}
            )
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }

        pagingAdapter.setOnItemClick(::onClick)
        subscribe()
        loadState()
    }

    private fun subscribe(){
        lifecycleScope.launchWhenCreated {
            viewModel.komik.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

    private fun onClick(id: String){
        val action = NewAnimeFragmentDirections.actionNewAnimeFragmentToDetailFragment(id)
        findNavController().navigate(action)
    }

    private fun loadState(){
        pagingAdapter.addLoadStateListener { loadState ->
            binding.apply {
                rvKomik.isVisible = loadState.source.refresh is LoadState.NotLoading
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading

                rvKomik.isVisible = !(loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached && pagingAdapter.itemCount < 1)
            }
        }
    }

}