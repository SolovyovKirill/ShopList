package com.shoppinglist.presentation.viewmodel.mainviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shoppinglist.data.ShopListRepositoryImpl
import com.shoppinglist.domain.model.ShopItem
import com.shoppinglist.domain.usecase.deleteshopitemusecase.DeleteShopItemUseCase
import com.shoppinglist.domain.usecase.editshopitemusecase.EditShopItemUseCase
import com.shoppinglist.domain.usecase.getshoplistusecase.GetShopListUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopListLiveData = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch{
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnableShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enable = !shopItem.enable)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}