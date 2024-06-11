package com.example.pricecheckstationkotlin.domain.repository.usecase

import com.example.pricecheckstationkotlin.domain.repository.ItemRepository
import com.example.pricecheckstationkotlin.model.data.Item

class InsertItemUseCase (
    private val repository: ItemRepository
    ) {
        suspend operator fun invoke(item: Item): Boolean {
            // TODO
            return repository.insertItem(item)
        }
    }