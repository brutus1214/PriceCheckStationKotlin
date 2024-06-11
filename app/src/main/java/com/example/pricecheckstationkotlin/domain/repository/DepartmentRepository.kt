package com.example.pricecheckstationkotlin.domain.repository

import com.example.pricecheckstationkotlin.model.data.Department
import kotlinx.coroutines.flow.Flow

interface DepartmentRepository {

    fun getDepartment(): Flow<Department>

    suspend fun getDepartmentById(id: Int): Department

    suspend fun insertDepartment(department: Department): Boolean

}