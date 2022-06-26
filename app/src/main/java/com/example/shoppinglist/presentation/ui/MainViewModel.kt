package com.example.shoppinglist.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.model.ShopItem
import com.example.shoppinglist.domain.use_cases.AddShopItemUseCase
import com.example.shoppinglist.domain.use_cases.DeleteShopItemUseCase
import com.example.shoppinglist.domain.use_cases.EditShopItemUseCase
import com.example.shoppinglist.domain.use_cases.GetShopListUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getShopListUseCase: GetShopListUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase,
    private val addShopItemUseCase: AddShopItemUseCase
) : ViewModel() {

    fun getShopList(): LiveData<List<ShopItem>> = getShopListUseCase.getShopList()

    fun changedEnabledState(item: ShopItem) =
        editShopItemUseCase.editShopItem(item.apply { isEnabled = !isEnabled })

    fun deleteShopItem(item: ShopItem) = deleteShopItemUseCase.deleteShopItem(item)

    fun addShopItem(item: ShopItem) {
        viewModelScope.launch {
            addShopItemUseCase.addShopItem(item)
        }
    }
}