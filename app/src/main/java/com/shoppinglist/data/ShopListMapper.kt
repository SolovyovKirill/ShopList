package com.shoppinglist.data

import com.shoppinglist.domain.model.ShopItem
import javax.inject.Inject

class ShopListMapper @Inject constructor(){

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enable = shopItem.enable
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        id = shopItemDbModel.id,
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        enable = shopItemDbModel.enable
    )

    fun mapListDbModelToEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}