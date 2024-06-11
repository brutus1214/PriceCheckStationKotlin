package com.example.pricecheckstationkotlin.domain.repository.usecase

data class VendorUseCases(
    val getVendor: GetVendorUseCase,
    val getVendorById: GetVendorByIdUseCase,
    val insertVendor: InsertVendorUseCase
)
