package com.note.cafe.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.note.cafe.R
import com.note.cafe.databinding.ViewholderSizeBinding
import com.note.cafe.model.StringDiffCallback

class SizeAdapter : ListAdapter<String, SizeAdapter.ViewHolder>(StringDiffCallback) {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class ViewHolder(val binding: ViewholderSizeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderSizeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val context = holder.itemView.context
        getItem(position)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if (selectedPosition == position) {
            holder.binding.img.setBackgroundResource(R.drawable.orange_bg)
        } else {
            holder.binding.img.setBackgroundResource(R.drawable.size_bg)
        }

        val imageSize = when (position) {
            0 -> 45.dpToPx(context)
            1 -> 50.dpToPx(context)
            2 -> 55.dpToPx(context)
            3 -> 65.dpToPx(context)
            else -> 70.dpToPx(context)
        }

        val layoutParams = holder.binding.img.layoutParams
        layoutParams.width = imageSize
        layoutParams.height = imageSize
        holder.binding.img.layoutParams = layoutParams
    }

    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }
}