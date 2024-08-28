package com.note.cafe.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.note.cafe.fragment.DetailFragment
import com.note.cafe.databinding.ViewholderPopularBinding
import com.note.cafe.model.ItemsModel

class PopularAdapter : ListAdapter<ItemsModel, PopularAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(val binding: ViewholderPopularBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderPopularBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.titleTxt.text = item.title
        holder.binding.priceTxt.text = "$-${item.price}"
        holder.binding.ratingBar.rating = item.rating.toFloat()
        holder.binding.extraTxt.text = item.extra

        Glide.with(holder.itemView)
            .load(item.picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailFragment::class.java)
            intent.putExtra("object", item)
            context.startActivity(intent)
        }
    }
}
class DiffCallback : DiffUtil.ItemCallback<ItemsModel>() {
    override fun areItemsTheSame(oldItem: ItemsModel, newItem: ItemsModel): Boolean {

        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ItemsModel, newItem: ItemsModel): Boolean {

        return oldItem == newItem
    }
}
