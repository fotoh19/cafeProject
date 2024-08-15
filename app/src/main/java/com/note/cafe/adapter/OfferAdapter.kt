package com.note.cafe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.note.cafe.databinding.ViewholderOfferBinding
import com.note.cafe.model.ItemsModel

class OfferAdapter(val items: MutableList<ItemsModel>) :
    RecyclerView.Adapter<OfferAdapter.Viewholder>() {
    private var context: Context? = null

    class Viewholder(val binding: ViewholderOfferBinding) :
        RecyclerView.ViewHolder(binding.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        context = parent.context
        val binding =ViewholderOfferBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int =items.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binding.titleTxt.text=items[position].title
        holder.binding.priceTxt.text="$"+items[position].price.toString()

        Glide.with(holder.itemView)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {

        }
    }


}