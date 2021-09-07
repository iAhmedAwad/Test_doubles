package com.trianglz.test_doubles.common.posts.presentation.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trianglz.test_doubles.common.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.databinding.ItemPostBinding
import javax.inject.Inject

class PostsAdapter @Inject constructor() :
    ListAdapter<PostDomainModel, RecyclerView.ViewHolder>(PostsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? PostViewHolder)?.bindViews(getItem(position))
    }

    //region ViewHolders
    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindViews(post: PostDomainModel) {
            binding.apply {
                titleTv.text = post.title.trim()
                bodyTv.text = post.body.trim()
            }

        }

    }

    //endregion
}

class PostsDiffUtil : DiffUtil.ItemCallback<PostDomainModel>() {
    override fun areItemsTheSame(oldItem: PostDomainModel, newItem: PostDomainModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PostDomainModel, newItem: PostDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

}
