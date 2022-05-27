package com.wahidabd.mangain.view.detail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.mangain.R
import com.wahidabd.mangain.core.Status
import com.wahidabd.mangain.data.models.room.HistoryData
import com.wahidabd.mangain.databinding.FragmentReaderHistoryBinding
import com.wahidabd.mangain.utils.quickShowToast
import com.wahidabd.mangain.view.detail.adapter.ReaderAdapter
import com.wahidabd.mangain.viewmodel.DetailMangaViewModel
import com.wahidabd.mangain.viewmodel.LocalViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.time.LocalDateTime

@AndroidEntryPoint
class ReaderHistoryFragment : Fragment() {

    private var _binding: FragmentReaderHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailMangaViewModel by viewModels()
    private val localViewModel: LocalViewModel by viewModels()
    private val args: ReaderHistoryFragmentArgs by navArgs()

    private lateinit var readerAdapter: ReaderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentReaderHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun subscribe() {
        viewModel.readerResource.observe(viewLifecycleOwner) {
            when (it.status) {
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

        viewModel.reader.observe(viewLifecycleOwner) {
            binding.tvTitle.text = it.title!!
            readerAdapter.setData = it.data

            binding.btnNext.isEnabled = it.next != null
            binding.btnPrev.isEnabled = it.prev != null

            val historyData = HistoryData(
                id = args.idKomik,
                id_chapter = args.id,
                title = args.titleKomik,
                cover = args.cover,
                chapter = it.title.replace(args.titleKomik, "").trim(),
                updated_at = LocalDateTime.now().toString()
            )

            checkHistory(historyData)

            binding.btnNext.setOnClickListener { _ ->
                val action = ReaderHistoryFragmentDirections.actionReaderHistoryFragmentSelf(
                    it.next!!,
                    args.idKomik,
                    args.titleKomik,
                    args.cover
                )
                findNavController().navigate(action)
            }

            binding.btnPrev.setOnClickListener { _ ->
                val action = ReaderHistoryFragmentDirections.actionReaderHistoryFragmentSelf(
                    it.prev!!,
                    args.idKomik,
                    args.titleKomik,
                    args.cover
                )
                findNavController().navigate(action)
            }

            binding.progressBar.visibility = View.GONE
            binding.rvReader.visibility = View.VISIBLE
        }
    }

    private fun checkHistory(data: HistoryData){
        localViewModel.historyById(args.id).observe(viewLifecycleOwner) { res ->
            val status = res != null

            if (status){
                localViewModel.updateHistory(data.id, data.id_chapter, data.chapter)
                Timber.e("UPDATE HISTORY")
            }else{
                localViewModel.insertHistory(data)
                Timber.e("INSERT HISTORY")
            }
        }
    }

}