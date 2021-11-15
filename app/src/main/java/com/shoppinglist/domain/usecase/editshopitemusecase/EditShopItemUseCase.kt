package com.shoppinglist.domain.usecase.editshopitemusecase

import com.shoppinglist.domain.repository.ShopListRepository
import com.shoppinglist.domain.shopitem.ShopItem

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}