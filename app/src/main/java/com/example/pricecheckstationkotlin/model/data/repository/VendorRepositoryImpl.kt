package com.example.pricecheckstationkotlin.model.data.repository

import com.example.pricecheckstationkotlin.domain.repository.VendorRepository
import com.example.pricecheckstationkotlin.model.data.Vendor
import kotlinx.coroutines.flow.Flow

class VendorRepositoryImpl: VendorRepository {
    override fun getVendor(): Flow<Vendor> {
        TODO("Not yet implemented")
    }

    override suspend fun getVendorById(id: Int): Vendor {
        TODO("Not yet implemented")
    }

    override suspend fun insertVendor(vendor: Vendor): Boolean {
        TODO("Not yet implemented")
    }
}