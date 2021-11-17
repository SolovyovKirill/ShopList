package com.shoppinglist.presentation.viewmodel.shopitemviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shoppinglist.data.ShopListRepositoryImpl
import com.shoppinglist.domain.shopitem.ShopItem
import com.shoppinglist.domain.usecase.addshopitemusecase.AddShopItemUseCase
import com.shoppinglist.domain.usecase.editshopitemusecase.EditShopItemUseCase
import com.shoppinglist.domain.usecase.getshopitemusecase.GetShopItemUseCase

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val _errorInputNameLiveData = MutableLiveData<Boolean>()
    val errorInputNameLiveData: LiveData<Boolean>
        get() = _errorInputNameLiveData

    private val _errorInputCountLiveData = MutableLiveData<Boolean>()
    val errorInputCountLiveData: LiveData<Boolean>
        get() = _errorInputCountLiveData

    private val _shopItemLiveData = MutableLiveData<ShopItem>()
    val shopItemLiveData: LiveData<ShopItem>
        get() = _shopItemLiveData

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getShopItemId(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
        _shopItemLiveData.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name, count)
        if (fieldValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name, count)
        if (fieldValid) {
            _shopItemLiveData.value?.let {
                val item = it.copy(name = name, count = count)
                editShopItemUseCase.editShopItem(item)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String = inputName?.trim() ?: ""

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }

    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputNameLiveData.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCountLiveData.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputNameLiveData.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCountLiveData.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }


}