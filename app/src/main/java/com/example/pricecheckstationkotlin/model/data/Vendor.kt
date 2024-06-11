package com.example.pricecheckstationkotlin.model.data

data class Vendor(
    var id: Int = 0,
    var name: String = ""
)

class VendorList() {

    companion object {
        var vendorList = mutableListOf<Vendor>()
    }

    fun getVendor(id: Int): Vendor? {
        return vendorList.find { it.id == id }
    }

    fun getVendorString(id: Int): String {
        return vendorList.find { it.id == id }?.name.toString()
    }

    fun addVendor(vendor: Vendor) {
        vendorList.add(vendor)
    }

    fun getIndexSize(): Int {
        return vendorList.size
    }
}