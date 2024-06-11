package com.example.pricecheckstationkotlin.domain.repository.usecase

import android.content.Context
import com.example.pricecheckstationkotlin.domain.repository.ItemRepository
import com.example.pricecheckstationkotlin.model.data.Item

class PrintItemUseCase (
    private val repository: ItemRepository
    ) {
        suspend operator fun invoke(item: Item, context: Context?): Boolean {
            return repository.printItem(item, context)
        }
    }