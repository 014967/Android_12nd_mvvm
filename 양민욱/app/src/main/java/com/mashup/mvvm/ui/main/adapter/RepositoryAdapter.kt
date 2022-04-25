package com.mashup.mvvm.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mashup.mvvm.data.model.Repository
import com.mashup.mvvm.databinding.ItemRepositoryBinding

class RepositoryAdapter : ListAdapter<Repository, RepositoryViewHolder>(repositoryDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = RepositoryViewHolder(
        ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBindRepository(getItem(position))
    }

    companion object {
        private val repositoryDiffCallback = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(
                oldItem: Repository, newItem: Repository
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Repository, newItem: Repository
            ) = oldItem == newItem
        }
    }
}

class RepositoryViewHolder(
    private val viewBinding: ItemRepositoryBinding
) : RecyclerView.ViewHolder(viewBinding.root) {
    fun onBindRepository(repository: Repository) = viewBinding.apply {
        Glide.with(root.context)
            .load(repository.owner.avatarUrl)
            .into(viewBinding.imgProfile)

        viewBinding.tvRepositoryName.text = repository.name
        viewBinding.tvRepositoryLanguage.text = repository.language
    }
}