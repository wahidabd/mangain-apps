package com.wahidabd.mangain.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidabd.mangain.core.Status
import com.wahidabd.mangain.databinding.FragmentReaderBinding
import com.wahidabd.mangain.utils.quickShowToast
import com.wahidabd.mangain.view.detail.adapter.ReaderAdapter
import com.wahidabd.mangain.viewmodel.DetailMangaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReaderFragment : Fragment() {

    private var _binding: FragmentReaderBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailMangaViewModel by viewModels()
    private val args: ReaderFragmentArgs by navArgs()

    private lateinit var readerAdapter: ReaderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentReaderBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        readerAdapter = ReaderAdapter()
        binding.rvReader.apply {
            adapter = readerAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            viewModel.reader(args.id)
            subscribe()
        }

        viewModel.reader(args.id)
        subscribe()
    }

    private fun subscribe(){
        viewModel.readerResource.observe(viewLifecycleOwner){
            when(it.status){
                Status.LOADING -> {
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.rvReader.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progressCircular.visibility = View.GONE
                    quickShowToast(it.message.toString())
                }
                else -> {}
            }
        }

        viewModel.reader.observe(viewLifecycleOwner){
            binding.tvTitle.text = it.title
            readerAdapter.setData = it.data

            binding.progressCircular.visibility = View.GONE
            binding.rvReader.visibility = View.VISIBLE
            binding.swipeRefresh.isRefreshing = false
        }
    }

}