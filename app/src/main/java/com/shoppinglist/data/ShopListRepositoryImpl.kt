package com.shoppinglist.data

import com.shoppinglist.domain.repository.ShopListRepository
import com.shoppinglist.domain.shopitem.ShopItem
import java.lang.RuntimeException

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem = getShopItem(shopItem.id)
        shopList.remove(oldShopItem)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it -> it.id == shopItemId }
            ?: throw RuntimeException("Element with id $shopItemId no found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}