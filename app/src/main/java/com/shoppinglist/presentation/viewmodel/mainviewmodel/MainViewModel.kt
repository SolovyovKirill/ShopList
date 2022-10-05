package com.shoppinglist.presentation.viewmodel.mainviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppinglist.domain.model.ShopItem
import com.shoppinglist.domain.usecase.deleteshopitemusecase.DeleteShopItemUseCase
import com.shoppinglist.domain.usecase.editshopitemusecase.EditShopItemUseCase
import com.shoppinglist.domain.usecase.getshoplistusecase.GetShopListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
) : ViewModel() {

    val shopListLiveData = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
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