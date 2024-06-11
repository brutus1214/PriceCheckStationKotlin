package com.example.pricecheckstationkotlin.domain.repository

import android.content.Context
import com.example.pricecheckstationkotlin.model.data.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getItem(): Flow<Item>

    suspend fun getItemById(item: Item, context: Context?): Item

    suspend fun insertItem(item: Item): Boolean

    suspend fun printItem(item: Item, context: Context?): Boolean
}