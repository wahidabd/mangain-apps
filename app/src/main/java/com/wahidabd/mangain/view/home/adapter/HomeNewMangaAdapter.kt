package com.wahidabd.mangain.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wahidabd.mangain.data.models.NewManga
import com.wahidabd.mangain.data.models.PopularDay
import com.wahidabd.mangain.databinding.ItemHomeNewBinding
import com.wahidabd.mangain.databinding.ItemHomePopularBinding
import com.wahidabd.mangain.utils.setFlag

class HomeNewMangaAdapter : RecyclerView.Adapter<HomeNewMangaAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<NewManga>() {
        override fun areItemsTheSame(oldItem: NewManga, newItem: NewManga): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NewManga, newItem: NewManga): Boolean =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this,  differCallback)

    var setData: List<NewManga>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    private var onItemClick: ((String) -> Unit)? = null
    fun setOnItemClicked(listener: (String) -> Unit){onItemClick = listener}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeNewMangaAdapter.ViewHolder {
        val binding = ItemHomeNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeNewMangaAdapter.ViewHolder, position: Int) {
        holder.bind(setData[position], onItemClick)
    }

    override fun getItemCount(): Int = setData.size

    inner class ViewHolder(private val binding: ItemHomeNewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: NewManga, onItemClick: ((String) -> Unit)?){
            binding.apply {
                tvTitle.text = data.title
                tvType.text = data.type
                tvRating.text = data.rating
                tvViews.text = data.views
                tvStatus.text = data.status
                tvColor.text = data.color

                imgFlag.setFlag(data.type)
                imgCover.load(data.cover)

                rootView.setOnClickListener {
                    onItemClick?.let { onItemClick(data.id) }
                }

            }
        }
    }

}