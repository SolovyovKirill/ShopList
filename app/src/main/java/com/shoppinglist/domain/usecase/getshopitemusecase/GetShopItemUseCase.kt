package com.shoppinglist.domain.usecase.getshopitemusecase

import com.shoppinglist.domain.repository.ShopListRepository
import com.shoppinglist.domain.shopitem.ShopItem

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun getShopItem(shopItemId: Int): ShopItem =
        shopListRepository.getShopItem(shopItemId)
}