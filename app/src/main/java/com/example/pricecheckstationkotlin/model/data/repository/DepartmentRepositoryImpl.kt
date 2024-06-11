package com.example.pricecheckstationkotlin.model.data.repository

import com.example.pricecheckstationkotlin.domain.repository.DepartmentRepository
import com.example.pricecheckstationkotlin.model.data.Department
import kotlinx.coroutines.flow.Flow

class DepartmentRepositoryImpl: DepartmentRepository {
    override fun getDepartment(): Flow<Department> {
        TODO("Not yet implemented")
    }

    override suspend fun getDepartmentById(id: Int): Department {
        TODO("Not yet implemented")
    }

    override suspend fun insertDepartment(department: Department): Boolean {
        TODO("Not yet implemented")
    }
}