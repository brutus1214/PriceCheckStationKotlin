package com.example.pricecheckstationkotlin.domain.repository.usecase

import com.example.pricecheckstationkotlin.domain.repository.DepartmentRepository
import com.example.pricecheckstationkotlin.model.data.Department
import kotlinx.coroutines.flow.Flow

class GetDepartmentUseCase (
    private val repository: DepartmentRepository
    ) {
        operator fun invoke(): Flow<Department> {
            // TODO
            var department = Department()
            return repository.getDepartment()
        }
    }