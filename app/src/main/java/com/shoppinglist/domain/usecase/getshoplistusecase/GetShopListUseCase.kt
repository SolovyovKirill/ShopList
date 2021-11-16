package com.shoppinglist.domain.usecase.getshoplistusecase

import androidx.lifecycle.LiveData
import com.shoppinglist.domain.repository.ShopListRepository
import com.shoppinglist.domain.shopitem.ShopItem

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> = shopListRepository.getShopList()
}