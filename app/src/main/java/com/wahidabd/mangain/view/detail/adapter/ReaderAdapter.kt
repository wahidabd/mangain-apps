package com.wahidabd.mangain.view.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wahidabd.mangain.data.models.DataChapterDetail
import com.wahidabd.mangain.databinding.ItemReaderBinding
import com.wahidabd.mangain.utils.circularProgress
import com.wahidabd.mangain.utils.setImageChapter

class ReaderAdapter(private val context: Context) : RecyclerView.Adapter<ReaderAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DataChapterDetail>() {
        override fun areItemsTheSame(oldItem: DataChapterDetail, newItem: DataChapterDetail): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: DataChapterDetail, newItem: DataChapterDetail): Boolean =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this,  differCallback)

    var setData: List<DataChapterDetail>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReaderAdapter.ViewHolder {
        val binding = ItemReaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReaderAdapter.ViewHolder, position: Int) {
       holder.bind(setData[position])
    }

    override fun getItemCount(): Int = setData.size

    inner class ViewHolder(private val binding: ItemReaderBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: DataChapterDetail){
//            binding.img.load(data)
            binding.tvNum.text = data.num.toString()

            val progress = circularProgress(context)
            binding.img.setImageChapter(data.img, progress)
//            binding.img.load(data.img)

        }
    }
}