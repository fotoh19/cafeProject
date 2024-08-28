package com.note.cafe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.note.cafe.service.ChangeNumberItemsListener
import com.note.cafe.databinding.ViewholderCartBinding
import com.note.cafe.model.ItemsModel
import com.note.cafe.service.ManagmentCart

class CartAdapter(
    context: ArrayList<ItemsModel>,
    var changeNumberItemListener: ChangeNumberItemsListener? = null
) : ListAdapter<ItemsModel, CartAdapter.Viewholder>(DiffCallback()) {

    class Viewholder(val binding: ViewholderCartBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    private val managementCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = getItem(position)

        holder.binding.titleTxt.text = item.title
        holder.binding.feeEacheItem.text = "$${item.price}"
        holder.binding.totalEacheItem.text = "$${Math.round(item.numberInCart * item.price)}"
        holder.binding.numberItemTxt.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.picCart)

        holder.binding.plusCartBtn.setOnClickListener {
            managementCart.plusItem(currentList.toMutableList(), position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }
            })
        }

        holder.binding.minusCartBtn.setOnClickListener {
            managementCart.minusItem(currentList.toMutableList(), position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }
            })
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
}