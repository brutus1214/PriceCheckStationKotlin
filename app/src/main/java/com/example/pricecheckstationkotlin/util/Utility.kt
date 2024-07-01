package com.example.pricecheckstationkotlin.util

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.util.Date

fun Utility() {
    // TODO
}

fun getCurrentDate():String{
    val sdf = SimpleDateFormat("MM-dd-YYYY")
    return sdf.format(Date())
}
object GlobalVariable {
    var context: Context? = null
}

fun getSaleDate(d: String): ChronoLocalDate {
    return LocalDate.parse(d)
}

fun IsItemOnSale(start: String, end: String): Boolean {
    if ((start != "") && (end != "")) {
        val saleStart: ChronoLocalDate = getSaleDate(start.toString())
        val saleEnd: ChronoLocalDate = getSaleDate(end.toString())
        if ((LocalDate.now() >= saleStart) && (LocalDate.now() <= saleEnd)) {
            return true
        }
    }
    return false
}
