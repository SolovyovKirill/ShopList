package com.shoppinglist.presentation.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.shoppinglist.domain.shopitem.ShopItem

class ShopItemDiffCallback : DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean =
        oldItem == newItem
}