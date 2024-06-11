package com.example.pricecheckstationkotlin.model.data

import android.annotation.SuppressLint
import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager

class ConnectionDB {
    companion object {
        var conn: Connection? = null
    }

    @SuppressLint("NewApi")
    fun connDB(
        ip: String = "192.168.1.200",
        port: String = "1433",
        db: String = "lamss",
        username: String = "sa",
        password: String = ""
    ): Connection? {
        val ip: String = ip
        val port: String = port
        val db: String = db
        val username: String = username
        val password: String = password

        val a: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(a)
        var connURL: String? = null
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connURL = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+db+";"
            conn= DriverManager.getConnection(connURL, username, password)
            Log.i("PCSK:", "Successfully connected using \"net.sourceforge.jtds.jdbc.Driver\"!")
        }
        catch (e: Exception) {
            Log.e("PCSK: Error is ", e.message.toString())
        }
        return conn
    }

}