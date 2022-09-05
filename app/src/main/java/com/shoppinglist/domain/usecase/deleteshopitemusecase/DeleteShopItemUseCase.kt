package com.shoppinglist.domain.usecase.deleteshopitemusecase

import com.shoppinglist.domain.repository.ShopListRepository
import com.shoppinglist.domain.model.ShopItem

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}