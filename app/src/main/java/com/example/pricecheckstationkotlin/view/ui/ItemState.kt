package com.example.pricecheckstationkotlin.view.ui

import com.example.pricecheckstationkotlin.model.data.Item

data class ItemState(
    var item: Item = Item(),
    var po: Int = 0,
    var print: Boolean = false

)

