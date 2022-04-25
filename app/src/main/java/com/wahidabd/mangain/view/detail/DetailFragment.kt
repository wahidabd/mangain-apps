package com.wahidabd.mangain.view.detail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.wahidabd.mangain.R
import com.wahidabd.mangain.core.Status
import com.wahidabd.mangain.data.models.Chapter
import com.wahidabd.mangain.data.models.room.BookmarkData
import com.wahidabd.mangain.databinding.FragmentDetailBinding
import com.wahidabd.mangain.utils.circularProgress
import com.wahidabd.mangain.utils.quickShowToast
import com.wahidabd.mangain.view.detail.adapter.ChapterAdapter
import com.wahidabd.mangain.viewmodel.DetailMangaViewModel
import com.wahidabd.mangain.viewmodel.LocalViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailMangaViewModel by viewModels()
    private val localViewModel: LocalViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var chapterAdapter: ChapterAdapter
    private val dataChapter = ArrayList<Chapter>()

    private var status: Boolean? = null
    private var bookmarkData = BookmarkData("")

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

        binding.ivBookmark.setOnClickListener {
            if (status ==  true) deleteBookmark(args.id)
            else insertBookmark(bookmarkData)
        }

        viewModel.detail(args.id)
        checkFavorite(args.id)
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

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    bookmarkData = BookmarkData(
                        id = args.id,
                        title = data.title,
                        cover = data.cover,
                        type = data.type,
                        updated_at = LocalDateTime.now().toString()
                    )
                }

                progressBar.visibility = View.GONE
                constraint.visibility = View.VISIBLE
            }
        }
    }

    private fun insertBookmark(data: BookmarkData){
        localViewModel.insertBookmark(data)
        status = true
        checkFavorite(data.id)
    }

    private fun deleteBookmark(id: String){
        localViewModel.deleteBookmark(id)
        status = false
        checkFavorite(id)
    }

    private fun checkFavorite(id: String){
        localViewModel.bookmarkById(id).observe(viewLifecycleOwner) {
            status = it != null
            if (status == true){
                binding.ivBookmark.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_bookmark_fill)
                )
            }else{
                binding.ivBookmark.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_bookmark_out)
                )
            }
        }
    }

}