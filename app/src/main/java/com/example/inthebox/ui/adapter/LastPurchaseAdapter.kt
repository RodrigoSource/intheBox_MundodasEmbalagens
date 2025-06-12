package com.example.inthebox.ui.adapter

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inthebox.R
import com.example.inthebox.data.entity.CartItem

class LastPurchaseAdapter(private val items: List<CartItem>) :
    RecyclerView.Adapter<LastPurchaseAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.item_name)
        val price = view.findViewById<TextView>(R.id.item_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_last_purchase, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.price.text = "R$ %.2f".format(item.price * item.quantity)
    }

    override fun getItemCount() = items.size
}