package com.example.pricecheckstationkotlin.domain.repository.usecase

data class DepartmentUseCases(
    val getDepartment: GetDepartmentUseCase,
    val getDepartmentById: GetDepartmentByIdUseCase,
    val insertDepartment: InsertDepartmentUseCase
)
