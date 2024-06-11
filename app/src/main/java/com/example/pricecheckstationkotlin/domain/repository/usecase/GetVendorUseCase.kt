package com.example.pricecheckstationkotlin.domain.repository.usecase

import com.example.pricecheckstationkotlin.domain.repository.VendorRepository
import com.example.pricecheckstationkotlin.model.data.Vendor
import kotlinx.coroutines.flow.Flow

class GetVendorUseCase (
    private val repository: VendorRepository
    ) {
        operator fun invoke(): Flow<Vendor> {
            // TODO
            var vendor = Vendor()
            return repository.getVendor()
        }
    }