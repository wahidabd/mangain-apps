package com.wahidabd.mangain.view.bookmark.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.mangain.data.models.room.BookmarkData
import com.wahidabd.mangain.data.models.room.HistoryData
import com.wahidabd.mangain.databinding.ItemBookmarkBinding
import com.wahidabd.mangain.databinding.ItemHistoryBinding
import com.wahidabd.mangain.utils.circularProgress
import com.wahidabd.mangain.utils.setImageChapter

class HistoryAdapter (private val context: Context) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<HistoryData>() {
        override fun areItemsTheSame(oldItem: HistoryData, newItem: HistoryData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: HistoryData, newItem: HistoryData): Boolean =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this,  differCallback)

    var setData: List<HistoryData>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    private var onItemClick: ((HistoryData) -> Unit)? = null
    fun setOnItemClicked(listener: (HistoryData) -> Unit){onItemClick = listener}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.ViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.bind(setData[position], onItemClick)
    }

    override fun getItemCount(): Int = setData.size

    inner class ViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: HistoryData, onItemClick: ((HistoryData) -> Unit)?){
            binding.apply {
                tvTitle.text = data.title
                tvChapter.text = data.chapter

                val progress = circularProgress(context)
                imgCover.setImageChapter(data.cover!!, progress)

                rootView.setOnClickListener {
                    onItemClick?.let { onItemClick(data) }
                }
            }
        }
    }

}