package com.example.pricecheckstationkotlin.domain.repository.usecase

import com.example.pricecheckstationkotlin.domain.repository.DepartmentRepository
import com.example.pricecheckstationkotlin.model.data.Department

class GetDepartmentByIdUseCase (
    private val repository: DepartmentRepository
    ) {
        suspend operator fun invoke(id: Int): Department {
            // TODO
            var department = Department()
            return repository.getDepartmentById(id)
        }
    }