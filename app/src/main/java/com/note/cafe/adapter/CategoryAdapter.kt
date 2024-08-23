package com.note.cafe.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.note.cafe.R
import com.note.cafe.databinding.ViewholderCategoryBinding
import com.note.cafe.model.CategoryDiffCallback
import com.note.cafe.model.CategoryModel

class CategoryAdapter :
    ListAdapter<CategoryModel, CategoryAdapter.ViewHolder>(CategoryDiffCallback) {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class ViewHolder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = getItem(position)
        holder.binding.titleCategory.text = item.title

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if (selectedPosition == position) {
            holder.binding.titleCategory.setBackgroundResource(R.drawable.orange_bg)
        } else {
            holder.binding.titleCategory.setBackgroundResource(R.drawable.editetextbg)
        }
    }
}