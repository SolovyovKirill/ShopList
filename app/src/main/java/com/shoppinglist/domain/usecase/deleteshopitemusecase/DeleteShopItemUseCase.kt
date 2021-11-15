package com.shoppinglist.domain.usecase.deleteshopitemusecase

import com.shoppinglist.domain.repository.ShopListRepository
import com.shoppinglist.domain.shopitem.ShopItem

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}