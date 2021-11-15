package com.shoppinglist.domain.usecase.getshoplistusecase

import com.shoppinglist.domain.repository.ShopListRepository
import com.shoppinglist.domain.shopitem.ShopItem

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): List<ShopItem> = shopListRepository.getShopList()
}