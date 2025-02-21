package com.epitech.cashmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    val cart : List<Product>,
    val itemOnClickListener: View.OnClickListener
) : RecyclerView.Adapter<CartAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.item_cart)
        val titleView = cardView.findViewById<TextView>(R.id.title)
        val priceView = cardView.findViewById<TextView>(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = cart[position]
        holder.cardView.setOnClickListener(itemOnClickListener)
        holder.cardView.tag = position
        holder.titleView.text = product.title
        holder.priceView.text = product.price.toString()
    }

    override fun getItemCount(): Int {
        return cart.size
    }

}