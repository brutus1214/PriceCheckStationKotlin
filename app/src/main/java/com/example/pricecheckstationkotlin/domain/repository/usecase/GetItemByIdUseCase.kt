package com.example.pricecheckstationkotlin.domain.repository.usecase

import android.content.Context
import com.example.pricecheckstationkotlin.domain.repository.ItemRepository
import com.example.pricecheckstationkotlin.model.data.InvalidItemException
import com.example.pricecheckstationkotlin.model.data.Item

class GetItemByIdUseCase (
    private val repository: ItemRepository
    ) {
        @Throws(InvalidItemException::class)
        suspend operator fun invoke(item: Item, context: Context?): Item {
            if(item == null) {
                throw InvalidItemException("Item is not in DB")
            }
            return repository.getItemById(item, context)
        }
    }