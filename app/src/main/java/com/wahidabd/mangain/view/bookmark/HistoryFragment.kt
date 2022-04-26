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
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidabd.mangain.R
import com.wahidabd.mangain.databinding.FragmentHistoryBinding
import com.wahidabd.mangain.view.bookmark.adapter.HistoryAdapter
import com.wahidabd.mangain.viewmodel.LocalViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LocalViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val historyAdapter = HistoryAdapter(requireContext())
        binding.rvHistory.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }

        viewModel.allHistory.observe(viewLifecycleOwner){
            historyAdapter.setData = it
        }

        historyAdapter.setOnItemClicked {
            val action = BookmarkFragmentDirections.actionBookmarkFragmentToReaderFragment(it.id_chapter!!, it.id, it.chapter!!, it.cover!!)
            findNavController().navigate(action)
        }

    }

}