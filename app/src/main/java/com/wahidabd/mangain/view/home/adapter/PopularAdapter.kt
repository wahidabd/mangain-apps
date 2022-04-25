package com.wahidabd.mangain.view.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wahidabd.mangain.data.models.Popular
import com.wahidabd.mangain.data.models.PopularDay
import com.wahidabd.mangain.databinding.ItemHomePopularBinding
import com.wahidabd.mangain.databinding.ItemPopularBinding
import com.wahidabd.mangain.utils.circularProgress
import com.wahidabd.mangain.utils.setFlag
import com.wahidabd.mangain.utils.setImageChapter

class PopularAdapter(private val context: Context) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Popular>() {
        override fun areItemsTheSame(oldItem: Popular, newItem: Popular): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Popular, newItem: Popular): Boolean =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this,  differCallback)

    var setData: List<Popular>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    private var onItemClick: ((String) -> Unit)? = null
    fun setOnItemClicked(listener: (String) -> Unit){onItemClick = listener}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.ViewHolder {
        val binding = ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
        holder.bind(setData[position], onItemClick)
    }

    override fun getItemCount(): Int = setData.size

    inner class ViewHolder(private val binding: ItemPopularBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Popular, onItemClick: ((String) -> Unit)?){
            binding.apply {
                tvTitle.text = data.title
                tvLove.text = data.love
                tvNum.text = data.num

                val progress = circularProgress(context)
                img.setImageChapter(data.cover, progress)

                rootView.setOnClickListener {
                    onItemClick?.let { onItemClick(data.id) }
                }

            }
        }
    }

}