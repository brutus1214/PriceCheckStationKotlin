package com.example.pricecheckstationkotlin.domain.repository

import com.example.pricecheckstationkotlin.model.data.Vendor
import kotlinx.coroutines.flow.Flow

interface VendorRepository {
    fun getVendor(): Flow<Vendor>

    suspend fun getVendorById(id: Int): Vendor

    suspend fun insertVendor(item: Vendor): Boolean
}