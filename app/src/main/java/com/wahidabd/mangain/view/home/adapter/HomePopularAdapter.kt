package com.wahidabd.mangain.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wahidabd.mangain.data.models.PopularDay
import com.wahidabd.mangain.databinding.ItemHomePopularBinding
import com.wahidabd.mangain.utils.setFlag

class HomePopularAdapter : RecyclerView.Adapter<HomePopularAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<PopularDay>() {
        override fun areItemsTheSame(oldItem: PopularDay, newItem: PopularDay): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PopularDay, newItem: PopularDay): Boolean =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this,  differCallback)

    var setData: List<PopularDay>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    private var onItemClick: ((String) -> Unit)? = null
    fun setOnItemClicked(listener: (String) -> Unit){onItemClick = listener}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePopularAdapter.ViewHolder {
        val binding = ItemHomePopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomePopularAdapter.ViewHolder, position: Int) {
        holder.bind(setData[position], onItemClick)
    }

    override fun getItemCount(): Int = setData.size

    inner class ViewHolder(private val binding: ItemHomePopularBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: PopularDay, onItemClick: ((String) -> Unit)?){
            binding.apply {
                tvTitle.text = data.title
                imgFlag.setFlag(data.type)
                tvUpdate.text = data.update_on
                img.load(data.cover)

                rootView.setOnClickListener {
                    onItemClick?.let { onItemClick(data.id) }
                }

            }
        }
    }
}