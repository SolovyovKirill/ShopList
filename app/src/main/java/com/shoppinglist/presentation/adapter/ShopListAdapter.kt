package com.shoppinglist.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shoppinglist.R
import com.shoppinglist.domain.shopitem.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    val list = listOf<ShopItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shop_disabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHoler: ShopItemViewHolder, position: Int) {
        val shopItem = list[position]

        viewHoler.tvName.text = shopItem.name
        viewHoler.tvCount.text = shopItem.count.toString()
        viewHoler.view.setOnLongClickListener {
            true
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }
}
