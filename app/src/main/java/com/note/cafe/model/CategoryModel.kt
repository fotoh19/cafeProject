package com.note.cafe.model

import androidx.recyclerview.widget.DiffUtil

data class CategoryModel(val title:String="",val id:Int=0)
object CategoryDiffCallback : DiffUtil.ItemCallback<CategoryModel>() {
    override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
        return oldItem == newItem
    }
}
