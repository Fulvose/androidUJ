package com.example.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.models.ItemsViewModel

class CustomAdapter(private val mList: List<ItemsViewModel>, private val ItemClickListener: ItemClickListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        holder.nameView.text = ItemsViewModel.name
        holder.priceView.text = ItemsViewModel.price

        holder.itemView.setOnClickListener {
            ItemClickListener.ItemClicked(position)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val nameView: TextView = itemView.findViewById(R.id.nameView)
        val priceView: TextView = itemView.findViewById(R.id.priceView)
    }
}