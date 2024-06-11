package com.example.pricecheckstationkotlin.model.data

data class Department(
    var id: Int = 0,
    var name: String = ""
)

class DepartmentList() {

    companion object {
        var departmentList = mutableListOf<Department>()
    }

    fun getDepartment(id: Int): Department? {
        return departmentList.find { it.id == id }
    }

    fun getDepartmentString(id: Int): String {
        return departmentList.find { it.id == id }?.name.toString()
    }

    fun addDepartment(dept: Department) {
        departmentList.add(dept)
    }

    fun getIndexSize(): Int {
        return departmentList.size
    }
}