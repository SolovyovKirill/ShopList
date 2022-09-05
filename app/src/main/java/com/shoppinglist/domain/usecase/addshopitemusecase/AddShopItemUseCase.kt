package com.shoppinglist.domain.usecase.addshopitemusecase

import com.shoppinglist.domain.repository.ShopListRepository
import com.shoppinglist.domain.model.ShopItem

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}