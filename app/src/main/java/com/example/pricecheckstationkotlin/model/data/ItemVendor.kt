package com.example.pricecheckstationkotlin.model.data

class ItemVendor {
    var vendorName: String = ""
    var mpq: Int = 0
    var reorderNumber: String = ""
    var cost: String = ""
}

class ItemVendorList() {

    companion object {
        var itemVendorList = mutableListOf<ItemVendor>()
    }

    fun getItemVendor(index: Int): ItemVendor? {
        return itemVendorList[index]
    }

    fun addItemVendor(itemVendor: ItemVendor) {
        itemVendorList.add(itemVendor)
    }

    fun getIndexSize(): Int {
        return itemVendorList.size
    }

    fun clear() {
        itemVendorList.clear()
    }
}