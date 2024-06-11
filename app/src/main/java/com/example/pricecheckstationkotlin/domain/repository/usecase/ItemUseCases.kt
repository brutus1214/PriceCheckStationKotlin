package com.example.pricecheckstationkotlin.domain.repository.usecase

data class ItemUseCases(
    val getItem: GetItemUseCase,
    val getItemById: GetItemByIdUseCase,
    val insertItem: InsertItemUseCase,
    val printItem: PrintItemUseCase
)
