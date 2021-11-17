package com.shoppinglist.presentation.viewmodel.mainviewmodel

import androidx.lifecycle.ViewModel
import com.shoppinglist.data.ShopListRepositoryImpl
import com.shoppinglist.domain.shopitem.ShopItem
import com.shoppinglist.domain.usecase.deleteshopitemusecase.DeleteShopItemUseCase
import com.shoppinglist.domain.usecase.editshopitemusecase.EditShopItemUseCase
import com.shoppinglist.domain.usecase.getshoplistusecase.GetShopListUseCase

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopListLiveData = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableShopItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(enable = !shopItem.enable)
        editShopItemUseCase.editShopItem(newItem)
    }
}