package com.wahidabd.mangain.view.detail

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wahidabd.mangain.data.models.Chapter
import com.wahidabd.mangain.databinding.BottomSheetChpaterBinding
import com.wahidabd.mangain.view.detail.adapter.ChapterBottomSheetAdapter

class BottomSheetChapter(val data: List<Chapter>) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetChpaterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = BottomSheetChpaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = ChapterBottomSheetAdapter()
        mAdapter.setData = data

        binding.rvChapter.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }

        mAdapter.setOnItemClicked(::navigate)

        binding.imgClose.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun navigate(id: String){
        dialog?.dismiss()
        val action = DetailFragmentDirections.actionDetailFragmentToReaderFragment(id)
        findNavController().navigate(action)
    }

}