package com.wahidabd.mangain.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.wahidabd.mangain.R
import com.wahidabd.mangain.core.Status
import com.wahidabd.mangain.databinding.FragmentHomeBinding
import com.wahidabd.mangain.view.home.adapter.HomeNewMangaAdapter
import com.wahidabd.mangain.view.home.adapter.HomePopularAdapter
import com.wahidabd.mangain.view.home.adapter.PopularAdapter
import com.wahidabd.mangain.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var popularAdapter: HomePopularAdapter
    private lateinit var newAdapter: HomeNewMangaAdapter
    private lateinit var pAdapter: PopularAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        setupView()
        subscribe()
        return binding.root
    }

    private fun setupView(){

        binding.tvShowAll.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNewAnimeFragment()
            findNavController().navigate(action)
        }

        binding.tvShow.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNewAnimeFragment()
            findNavController().navigate(action)
        }

        popularAdapter = HomePopularAdapter()
        binding.rvPopular.apply {
            adapter = popularAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            itemAnimator = DefaultItemAnimator()
        }

        newAdapter = HomeNewMangaAdapter()
        binding.rvNewManga.apply {
            adapter = newAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }

        pAdapter = PopularAdapter(requireContext())
        binding.rvPopularKomik.apply {
            adapter = pAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            itemAnimator = DefaultItemAnimator()
        }

        pAdapter.setOnItemClicked(::onClick)
        popularAdapter.setOnItemClicked(::onClick)
        newAdapter.setOnItemClicked(::onClick)

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            subscribe()
        }
    }

    private fun subscribe(){
        lifecycleScope.launchWhenCreated {
            viewModel.home.collectLatest {
                when(it.status){
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rlContent.visibility = View.GONE
                    }
                    Status.ERROR -> {}
                    Status.SUCCESS -> {
                        popularAdapter.setData = it.data?.home?.popular_day!!
                        newAdapter.setData = it.data.home.new_manga
                        pAdapter.setData = it.data.home.popular

                        binding.swipeRefresh.isRefreshing = false
                        binding.rlContent.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun onClick(id: String){
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}