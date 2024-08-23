package com.note.cafe.adapter

import com.note.cafe.fragment.CartFragment
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.note.cafe.service.ManagmentCart
import com.note.cafe.service.ChangeNumberItemsListener
import com.note.cafe.databinding.ViewholderCartBinding
import com.note.cafe.model.ItemsDiffCallback
import com.note.cafe.model.ItemsModel
import kotlin.math.roundToLong

class CartAdapter : ListAdapter<ItemsModel, CartAdapter.ViewHolder>(ItemsDiffCallback) {

        private val managmentCart:ManagmentCart = ManagmentCart(coontext = CartFragment())
    class ViewHolder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderCartBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.titleTxt.text = item.title
        holder.binding.feeEacheItem.text = "$${item.price}"
        holder.binding.totalEacheItem.text = "$${(item.numberInCart * item.price).roundToLong()}"
        holder.binding.numberItemTxt.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .into(holder.binding.picCart)

        holder.binding.plusCartBtn.setOnClickListener {
            managmentCart.plusItem(currentList, position, object : ChangeNumberItemsListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onChanged() {
                    submitList(currentList.toMutableList())
                    onChanged()
                }
            })
        }

        holder.binding.minusCartBtn.setOnClickListener {
            managmentCart.minusItem(currentList, position, object : ChangeNumberItemsListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onChanged() {
                    submitList(currentList.toMutableList())
                    onChanged()
                }
            })
        }
    }
}

