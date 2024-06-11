package com.example.pricecheckstationkotlin.util

import android.content.Context
import java.text.SimpleDateFormat
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