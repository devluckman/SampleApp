package com.edu.filmku.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edu.filmku.databinding.ItemMovieHorizontalBinding
import com.edu.filmku.domain.model.ItemMovieModel

/**
 *
 * Created by Lukmanul Hakim on  26/02/24
 * devs.lukman@gmail.com
 */
class NowPlayingAdapter(
    private val onClickAction :(ItemMovieModel) -> Unit
) : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    private val dataList = mutableListOf<ItemMovieModel>()

    class ViewHolder(private val binding: ItemMovieHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemMovieModel, onClickAction :(ItemMovieModel) -> Unit) {
            binding.apply {
                tvImbd.text = data.rating
                tvMovieName.text = data.title
                Glide.with(ivPoster)
                    .load(data.poster)
                    .into(ivPoster)
                root.setOnClickListener {
                    onClickAction.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieHorizontalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data, onClickAction)
    }

    fun submit(data : List<ItemMovieModel>) {
        dataList.clear()
        dataList.addAll(data)
        notifyItemChanged(0, dataList.size)
    }
}