package com.wahidabd.mangain.view.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.wahidabd.mangain.core.Status
import com.wahidabd.mangain.data.models.Chapter
import com.wahidabd.mangain.data.models.DataChapterDetail
import com.wahidabd.mangain.databinding.FragmentDetailBinding
import com.wahidabd.mangain.utils.Constant
import com.wahidabd.mangain.utils.quickShowToast
import com.wahidabd.mangain.view.detail.adapter.ChapterAdapter
import com.wahidabd.mangain.viewmodel.DetailMangaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailMangaViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var chapterAdapter: ChapterAdapter
    private val dataChapter = ArrayList<Chapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        setupView()
        subscribe()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener { findNavController().navigateUp() }
    }

    private fun setupView(){

        chapterAdapter = ChapterAdapter()
        binding.rvChapter.apply {
            adapter = chapterAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }

        chapterAdapter.setOnItemClicked {
            val action = DetailFragmentDirections.actionDetailFragmentToReaderFragment(it.id)
            findNavController().navigate(action)
        }

        binding.tvShowAll.setOnClickListener {
            BottomSheetChapter(dataChapter).show(parentFragmentManager, "TAG")
        }

        viewModel.detail(args.id)
    }

    @SuppressLint("SetTextI18n")
    private fun subscribe(){
        viewModel.resource.observe(viewLifecycleOwner){
            when(it.status){

                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.constraint.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.VISIBLE
                    quickShowToast(it.message.toString())
                }
                else -> {}
            }
        }

        viewModel.result.observe(viewLifecycleOwner){ data ->
            binding.apply {
                imageView.load(data.banner)
                imgItemPhoto.load(data.cover)
                tvTitle.text = data.title
                tvAuthor.text = ": " + data.author
                tvIllust.text = ": " + data.ilustrator
                tvType.text = ": " + data.type
                tvGraphic.text = ": " + data.grafis
                tvRating.text = ": " + data.rating
                tvStatus.text = ": " + data.status
                tvSynopsis.text = data.synopsis
                tvVotes.text = ": " + data.vote
                binding.tvNameGenre.text = ""
                data.genres.forEach { i -> tvNameGenre.append("\u2022 ${i.title}  ") }

                dataChapter.clear()
                chapterAdapter.setData = data.eps
                dataChapter.addAll(data.eps)

                progressBar.visibility = View.GONE
                constraint.visibility = View.VISIBLE
            }
        }
    }

}