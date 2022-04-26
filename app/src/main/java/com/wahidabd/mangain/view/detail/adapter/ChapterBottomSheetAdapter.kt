package com.wahidabd.mangain.view.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.mangain.data.models.Chapter
import com.wahidabd.mangain.databinding.ItemChapterBinding

class ChapterBottomSheetAdapter : RecyclerView.Adapter<ChapterBottomSheetAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Chapter>() {
        override fun areItemsTheSame(oldItem: Chapter, newItem: Chapter): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Chapter, newItem: Chapter): Boolean =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this,  differCallback)

    var setData: List<Chapter>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    private var onItemClick: ((Chapter) -> Unit)? = null
    fun setOnItemClicked(listener: (Chapter) -> Unit){onItemClick = listener}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(setData[position], onItemClick)
    }

    override fun getItemCount(): Int = setData.size

    inner class ViewHolder(private val binding: ItemChapterBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Chapter, onItemClick: ((Chapter) -> Unit)?){
            binding.apply {
                tvTitle.text = data.title

                rootView.setOnClickListener {
                    onItemClick?.let { onItemClick(data) }
                }
            }
        }
    }

}