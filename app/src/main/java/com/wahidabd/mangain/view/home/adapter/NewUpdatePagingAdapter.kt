package com.wahidabd.mangain.view.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.mangain.data.models.Komik
import com.wahidabd.mangain.databinding.ItemHomeNewBinding
import com.wahidabd.mangain.databinding.ItemNewKomikBinding
import com.wahidabd.mangain.utils.circularProgress
import com.wahidabd.mangain.utils.setFlag
import com.wahidabd.mangain.utils.setImageChapter

class NewUpdatePagingAdapter(private val context: Context) : PagingDataAdapter<Komik, NewUpdatePagingAdapter.ViewHolder>(
    diffUtilCallback) {

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
        val binding = ItemNewKomikBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemNewKomikBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Komik, onItemClick: ((String) -> Unit)?){
            binding.apply {
                tvTitle.text = data.title
                tvUpdate.text = data.update_on
                tvType.text = data.type

                val progress = circularProgress(context)
                imgFlag.setFlag(data.type)
                imgCover.setImageChapter(data.cover, progress)
            }
        }
    }

}