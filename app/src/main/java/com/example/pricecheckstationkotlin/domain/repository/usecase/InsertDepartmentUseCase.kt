package com.example.pricecheckstationkotlin.domain.repository.usecase

import com.example.pricecheckstationkotlin.domain.repository.DepartmentRepository
import com.example.pricecheckstationkotlin.model.data.Department

class InsertDepartmentUseCase (
    private val repository: DepartmentRepository
    ) {
        suspend operator fun invoke(department: Department):Boolean {
            // TODO
            return repository.insertDepartment(department)
        }
    }