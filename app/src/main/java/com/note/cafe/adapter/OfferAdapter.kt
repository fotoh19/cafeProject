package com.note.cafe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.note.cafe.databinding.ViewholderOfferBinding
import com.note.cafe.model.ItemsDiffCallback
import com.note.cafe.model.ItemsModel

class OfferAdapter : ListAdapter<ItemsModel, OfferAdapter.ViewHolder>(ItemsDiffCallback) {

    private var context: Context? = null

    class ViewHolder(val binding: ViewholderOfferBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ViewholderOfferBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.titleTxt.text = item.title
        holder.binding.priceTxt.text = "$${item.price}"

        Glide.with(holder.itemView)
            .load(item.picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {

        }
    }
}