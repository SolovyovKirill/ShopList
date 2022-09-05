package com.shoppinglist.domain.usecase.editshopitemusecase

import com.shoppinglist.domain.repository.ShopListRepository
import com.shoppinglist.domain.model.ShopItem

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}