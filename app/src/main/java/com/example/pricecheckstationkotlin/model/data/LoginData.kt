package com.example.pricecheckstationkotlin.model.data

class LoginData {
    var server: String = ""
    var userName: String = ""
    var password: String = ""
    var dbName: String = ""

    fun validateServer(server: String): Boolean {
        return true
    }

    fun validateUserName(userName: String): Boolean {
        return true
    }

    fun validatePassword(password: String): Boolean {
        return true
    }

    fun validateDbName(dbName: String): Boolean {
        return true
    }
}