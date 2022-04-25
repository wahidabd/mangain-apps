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
import androidx.recyclerview.widget.RecyclerView
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

        readerAdapter = ReaderAdapter(requireContext())
        binding.rvReader.apply {
            adapter = readerAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.rvReader.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(!binding.rvReader.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE){
                    binding.btnNext.visibility = View.VISIBLE
                    binding.btnPrev.visibility = View.VISIBLE
                }else{
                    binding.btnNext.visibility = View.GONE
                    binding.btnPrev.visibility = View.GONE
                }

            }
        })

        viewModel.reader(args.id)
        subscribe()
    }

    private fun subscribe(){
        viewModel.readerResource.observe(viewLifecycleOwner){
            when(it.status){
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvReader.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    quickShowToast(it.message.toString())
                }
                else -> {}
            }
        }

        viewModel.reader.observe(viewLifecycleOwner){
            binding.tvTitle.text = it.title
            readerAdapter.setData = it.data

            binding.btnNext.isEnabled = it.next != null
            binding.btnPrev.isEnabled = it.prev != null

            binding.btnNext.setOnClickListener { _ ->
                val action = ReaderFragmentDirections.actionReaderFragmentSelf(it.next!!)
                findNavController().navigate(action)
            }

            binding.btnPrev.setOnClickListener { _ ->
                val action = ReaderFragmentDirections.actionReaderFragmentSelf(it.prev!!)
                findNavController().navigate(action)
            }

            binding.progressBar.visibility = View.GONE
            binding.rvReader.visibility = View.VISIBLE
        }
    }

}