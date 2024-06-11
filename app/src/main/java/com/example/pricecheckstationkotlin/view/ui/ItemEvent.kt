package com.example.pricecheckstationkotlin.view.ui

import com.example.pricecheckstationkotlin.model.data.Item

sealed class ItemEvent {
    data class GetItem(val item: Item): ItemEvent()
    data class GetItemById(val item: Item): ItemEvent()
    data class InsertItem(val item: Item): ItemEvent()

    data class PrintItem(val item: Item): ItemEvent()

}