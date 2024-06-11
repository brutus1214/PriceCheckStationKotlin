package com.example.pricecheckstationkotlin.domain.repository.usecase

import com.example.pricecheckstationkotlin.domain.repository.VendorRepository
import com.example.pricecheckstationkotlin.model.data.Vendor

class InsertVendorUseCase (
    private val repository: VendorRepository
    ) {
        suspend operator fun invoke(vendor: Vendor): Boolean {
            // TODO
            return repository.insertVendor(vendor)
        }
    }