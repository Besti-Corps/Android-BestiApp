package com.dicoding.picodiploma.besti.view.home.ui.profile.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.picodiploma.besti.databinding.ItemReviewBinding
import com.dicoding.picodiploma.besti.dataclass.listReviewItem

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.UserViewHolder>() {

    private val list = ArrayList<listReviewItem>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class UserViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: listReviewItem) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }
            binding.apply {
                tvUsername.text = user.name
                tvDescription.text = user.comment
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: listReviewItem)
    }

    fun setList(users: ArrayList<listReviewItem>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }
}