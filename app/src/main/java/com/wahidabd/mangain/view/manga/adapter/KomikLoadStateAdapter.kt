package com.wahidabd.mangain.view.manga.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.mangain.databinding.LoadStateFooterBinding

class KomikLoadStateAdapter(private val retry: () -> Unit)
    :LoadStateAdapter<KomikLoadStateAdapter.LoadStateAdapter>() {

    override fun onBindViewHolder(holder: KomikLoadStateAdapter.LoadStateAdapter, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): KomikLoadStateAdapter.LoadStateAdapter {
        val binding = LoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateAdapter(binding)
    }

    inner class LoadStateAdapter(private val binding: LoadStateFooterBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.btnRetry.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState){
            with(binding){
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                tvError.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}