package com.wahidabd.mangain.view.detail

import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.core.widget.PopupWindowCompat
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wahidabd.mangain.data.models.Chapter
import com.wahidabd.mangain.databinding.BottomSheetChpaterBinding
import com.wahidabd.mangain.databinding.PopupFilterChapterBinding
import com.wahidabd.mangain.view.detail.adapter.ChapterBottomSheetAdapter
import java.util.*

class BottomSheetChapter(
    private val data: List<Chapter>,
    private val idKomik: String,
    private val titleKomik: String,
    private val cover: String
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetChpaterBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: ChapterBottomSheetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = BottomSheetChpaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = ChapterBottomSheetAdapter()
        mAdapter.setData = data

        binding.rvChapter.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }

        mAdapter.setOnItemClicked{
            dialog?.dismiss()
            val action = DetailFragmentDirections.actionDetailFragmentToReaderFragment(it.id, idKomik, it.title, cover)
            findNavController().navigate(action)
        }

        binding.edtSearch.doAfterTextChanged {
            search(it.toString())
        }

        binding.imgClose.setOnClickListener {
            dialog?.dismiss()
        }

        binding.ivFilter.setOnClickListener { popup() }
    }

    private fun search(q: String){
        val filtered = ArrayList<Chapter>()

        data.forEach {
            if (it.title.lowercase(Locale.ROOT).contains(q.lowercase(Locale.ROOT))){
                filtered.add(it)
            }
        }

        mAdapter.setData = filtered
    }

    private fun popup(){
        val popupBinding = PopupFilterChapterBinding.inflate(layoutInflater)
        val popupWindow = PopupWindow(
            popupBinding.root,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        popupWindow.elevation = 10f
        PopupWindowCompat.showAsDropDown(popupWindow, binding.ivFilter, 0, 0, Gravity.CLIP_VERTICAL)
        PopupWindowCompat.setWindowLayoutType(popupWindow, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
    }

}