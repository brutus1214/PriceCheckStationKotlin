package com.example.pricecheckstationkotlin.domain.repository.usecase

import com.example.pricecheckstationkotlin.domain.repository.VendorRepository
import com.example.pricecheckstationkotlin.model.data.Vendor

class GetVendorByIdUseCase (
    private val repository: VendorRepository
    ) {
        suspend operator fun invoke(id: Int): Vendor {
            // TODO
            return repository.getVendorById(id)
        }
    }