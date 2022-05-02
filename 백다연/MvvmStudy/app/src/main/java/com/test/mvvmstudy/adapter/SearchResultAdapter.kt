package com.test.mvvmstudy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.mvvmstudy.data.ResultDetail
import com.test.mvvmstudy.databinding.ItemSearchListBinding

class SearchResultAdapter :
    ListAdapter<ResultDetail, SearchResultAdapter.SearchViewHolder>(diffUtil) {

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onClick(view: View, searchData: ResultDetail)
    }

    class SearchViewHolder(private val binding: ItemSearchListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ResultDetail) {
            binding.searchData = item
            Glide.with(binding.root).load(item.owner.imgUrl).into(binding.profileImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemSearchListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (itemClick != null) {
            holder.itemView.setOnClickListener {
                itemClick?.onClick(it, getItem(position))
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ResultDetail>() {
            override fun areItemsTheSame(oldItem: ResultDetail, newItem: ResultDetail): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ResultDetail, newItem: ResultDetail): Boolean {
                return oldItem == newItem
            }


        }
    }
}