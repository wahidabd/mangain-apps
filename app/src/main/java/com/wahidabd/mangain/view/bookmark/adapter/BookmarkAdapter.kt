package com.wahidabd.mangain.view.bookmark.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.mangain.data.models.room.BookmarkData
import com.wahidabd.mangain.databinding.ItemBookmarkBinding
import com.wahidabd.mangain.utils.circularProgress
import com.wahidabd.mangain.utils.setImageChapter

class BookmarkAdapter (private val context: Context) : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<BookmarkData>() {
        override fun areItemsTheSame(oldItem: BookmarkData, newItem: BookmarkData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BookmarkData, newItem: BookmarkData): Boolean =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this,  differCallback)

    var setData: List<BookmarkData>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    private var onItemClick: ((String) -> Unit)? = null
    fun setOnItemClicked(listener: (String) -> Unit){onItemClick = listener}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkAdapter.ViewHolder {
        val binding = ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.ViewHolder, position: Int) {
        holder.bind(setData[position], onItemClick)
    }

    override fun getItemCount(): Int = setData.size

    inner class ViewHolder(private val binding: ItemBookmarkBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: BookmarkData, onItemClick: ((String) -> Unit)?){
            binding.apply {
                tvTitle.text = data.title

                val progress = circularProgress(context)
                img.setImageChapter(data.cover!!, progress)

                rootView.setOnClickListener {
                    onItemClick?.let { onItemClick(data.id) }
                }
            }
        }
    }

}