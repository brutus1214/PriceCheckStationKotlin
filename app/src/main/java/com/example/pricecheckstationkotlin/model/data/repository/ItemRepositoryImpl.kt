package com.example.pricecheckstationkotlin.model.data.repository

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.pricecheckstationkotlin.MainActivity
import com.example.pricecheckstationkotlin.domain.repository.ItemRepository
import com.example.pricecheckstationkotlin.model.data.ConnectionDB
import com.example.pricecheckstationkotlin.model.data.DepartmentList
import com.example.pricecheckstationkotlin.model.data.Item
import com.example.pricecheckstationkotlin.model.data.ItemVendor
import com.example.pricecheckstationkotlin.model.data.VendorList
import com.example.pricecheckstationkotlin.util.IsItemOnSale
import com.example.pricecheckstationkotlin.util.ean13
import com.example.pricecheckstationkotlin.util.getCurrentDate
import com.example.pricecheckstationkotlin.util.nobarcode
import com.example.pricecheckstationkotlin.util.upca12
import kotlinx.coroutines.flow.Flow
import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.sql.ResultSet
import java.sql.Statement

class ItemRepositoryImpl: ItemRepository {
    override fun getItem(): Flow<Item> {
        TODO("Not yet implemented")
    }

    override suspend fun getItemById(item: Item, context: Context?): Item {
        val conn = ConnectionDB.conn

        val deptList = DepartmentList()
        val vendorList = VendorList()

        if (conn != null) {
            try {
                val sqlStatement: String = "SELECT Description, " +
                        "SubDescription1, " +
                        "SubDescription2, " +
                        "SubDescription3, " +
                        "Price, " +
                        "SalePrice, " +
                        "SaleStartDate, " +
                        "SaleEndDate, " +
                        "id " +
                        "FROM item " +
                        "WHERE itemlookupcode = '" + item.upc + "'"

                val smt: Statement = conn!!.createStatement()
                val set: ResultSet = smt.executeQuery(sqlStatement)
                //Log.i("ItemRepositoryImpl UPC ---> ", item.upc)
                while (set.next()) {
                    item.description = set.getString(1)
                    item.desc1 = set.getString(2)
                    item.desc2 = set.getString(3)
                    item.desc3 = set.getString(4)
                    item.retailPrice = String.format("%.2f", set.getString(5).toDouble())
                    item.salePrice = String.format("%.2f", set.getString(6).toDouble())
                    if (set.getString(7) != null)
                        item.saleStart = set.getString(7).substring(0, 10)
                    if (set.getString(8) != null)
                        item.saleEnd = set.getString(8).substring(0, 10)
                    Log.i("PCSK: ItemS ---> ", item.saleStart)
                    Log.i("PCSK: ItemE---> ", item.saleEnd)

                    item.id = set.getString(9)

                }
            } catch (e: Exception) {
                Log.e("Error ---> Item Not Exist ---> ", e.message.toString())
            }
        }
        return item
    }

    override suspend fun insertItem(item: Item): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun printItem(item: Item, context: Context?): Boolean {
        /* Use old Windows Application to print in PrintServer --> Will Think about it
        val conn = ConnectionDB.conn

        if (conn != null) {
            try {
                val sqlStatement: String = "UPDATE item SET Consignment = 1 " +
                        "WHERE ItemLookupCode = '" + item.upc + "'"
                val smt3: Statement = conn!!.createStatement()
                val set3: ResultSet = smt3.executeQuery(sqlStatement)
                while (set3.next()) {
                    if (set3.getInt(1) != null) {
                        Log.i("PrintItem UPC ---> ", set3.getInt(1).toString())
                    }
                    else {
                        //
                    }
                }
                Log.i("PrintItem UPC2 ---> ", item.upc)
                set3.close()
            } catch (e: Exception) {
                Log.e("Error ---> PrintItem ---> ", e.message.toString())
                return false
            }
        }
        */

        //Log.i("Print Test", "Start")

        var barcode: String = ""
        // Decide which UPC oe EAN
        if (item.upc.length == 11) {
            barcode = upca12.toString()
        } else if (item.upc.length == 12) {
            barcode = ean13.toString()
        } else {
            // No Barcode print
            barcode = nobarcode.toString()
        }

        var templateBytes: ByteArray? = null

        try {
            // Convert template ZPL string to a UTF-8 encoded byte array, which will be sent as an extra with the intent
            templateBytes = barcode.toByteArray()
        } catch (e: UnsupportedEncodingException) {
            // Handle exception
        }
        // Define a hash map of variable data
        // Strings used for keys will be replaced by their corresponding values in your template file's ZPL
        var variableData: HashMap<String, String> = HashMap<String, String>()
        variableData.put("%DESC%", item.description)
        variableData.put("%DESC1%", item.desc1)
        variableData.put("%DESC2%", item.desc2)
        variableData.put("%DESC3%", item.desc3)
        variableData.put("%BARCODE%", item.upc)
        variableData.put("%DATE%", getCurrentDate())
        variableData.put("%DOLLAR%", "$")
        variableData.put("%PRICE%", item.retailPrice)
        //variableData.put("%UPC_CODE%", "12345678");
        var intent: Intent = Intent(context, MainActivity::class.java)
        intent.setComponent(
            ComponentName("com.zebra.printconnect",
                "com.zebra.printconnect.print.TemplatePrintWithContentService")
        )
        intent.putExtra("com.zebra.printconnect.PrintService.TEMPLATE_DATA", templateBytes)
        intent.putExtra("com.zebra.printconnect.PrintService.VARIABLE_DATA", variableData)
        /*
        intent.putExtra("com.zebra.printconnect.PrintService.RESULT_RECEIVER", buildIPCSafeReceiver(
            ResultReceiver(null) {
            @Override
            fun onReceiveResult(resultCode: Int, resultData: Bundle?): Unit {
                if (resultCode == 0) { // Result code 0 indicates success
                    // Handle successful print
                } else {
                    // Handle unsuccessful print
                    // Error message (null on successful print)
                    var errorMessage: String = resultData.getString("com.zebra.printconnect.PrintService.ERROR_MESSAGE");
                }
            }
        }))
        */
        context!!.startService(intent)

        return true
    }
}
