package com.wahidabd.mangain.view.manga.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.mangain.data.models.Komik
import com.wahidabd.mangain.databinding.ItemHomePopularBinding
import com.wahidabd.mangain.utils.circularProgress
import com.wahidabd.mangain.utils.setFlag
import com.wahidabd.mangain.utils.setImageChapter

class KomikPagingAdapter(private val context: Context): PagingDataAdapter<Komik, KomikPagingAdapter.ViewHolder>(
    diffUtilCallback
) {

    companion object{
        val diffUtilCallback = object : DiffUtil.ItemCallback<Komik>() {
            override fun areItemsTheSame(oldItem: Komik, newItem: Komik): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Komik, newItem: Komik): Boolean =
                oldItem == newItem
        }
    }

    private var onItemClick: ((String) -> Unit)? = null
    fun setOnItemClick(listener: (String) -> Unit){
        onItemClick = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem, onItemClick)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemHomePopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemHomePopularBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Komik, onItemClick: ((String) -> Unit)?){
            binding.apply {
                tvTitle.text = data.title
                imgFlag.setFlag(data.type)
//                img.load(data.cover)

                val progress = circularProgress(context)
                img.setImageChapter(data.cover, progress)

                if (data.update_on != null) tvUpdate.text = data.update_on
                else tvUpdate.visibility = View.GONE

                if(data.rating != null) tvRating.text = data.rating
                else {
                    tvRating.visibility = View.GONE
                    imageView2.visibility = View.GONE
                }

                rootView.setOnClickListener {
                    onItemClick?.let { onItemClick(data.id) }
                }
            }
        }
    }
}