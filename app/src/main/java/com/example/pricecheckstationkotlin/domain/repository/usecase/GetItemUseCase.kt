package com.example.pricecheckstationkotlin.domain.repository.usecase

import com.example.pricecheckstationkotlin.domain.repository.ItemRepository
import com.example.pricecheckstationkotlin.model.data.Item
import kotlinx.coroutines.flow.Flow

class GetItemUseCase (
    private val repository: ItemRepository
    ) {
        operator fun invoke(): Flow<Item> {
            // TODO
            var item = Item()
            return repository.getItem()
        }
    }