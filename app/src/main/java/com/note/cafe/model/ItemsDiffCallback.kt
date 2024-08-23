package com.note.cafe.model

import androidx.recyclerview.widget.DiffUtil

object ItemsDiffCallback : DiffUtil.ItemCallback<ItemsModel>() {
    override fun areItemsTheSame(oldItem: ItemsModel, newItem: ItemsModel): Boolean {
        return oldItem.numberInCart == newItem.numberInCart
    }

    override fun areContentsTheSame(oldItem: ItemsModel, newItem: ItemsModel): Boolean {
        return oldItem == newItem
    }
}