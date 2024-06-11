package com.example.pricecheckstationkotlin.model.data

data class Item(
    var upc: String = "",
    var lastSold: String = "",
    var description: String = "",
    var desc1: String = "",
    var desc2: String = "",
    var desc3: String = "",
    var inventory: String = "",
    var reorder: String = "",
    var extDesc: String = "",
    var retailPrice: String = "",
    var salePrice: String = "",
    var cost: String = "",
    var dept: Department = Department(),
    var vendor: Vendor = Vendor(),
    var saleStart: String = "",
    var saleEnd: String = "",
    var sevenDays : String = "",
    var fifteenDays : String = "",
    var thirtyDays : String = "",
    var id : String = "",
    var itemVendorList: ItemVendorList = ItemVendorList()
)

fun itemClear(item: Item) {
    //item.upc = ""
    item.lastSold = ""
    item.description = ""
    item.desc1 = ""
    item.desc2 = ""
    item.desc3 = ""
    item.inventory = ""
    item.reorder = ""
    item.extDesc = ""
    item.retailPrice = ""
    item.salePrice = ""
    item.cost = ""
    item.dept = Department()
    item.vendor = Vendor()
    item.saleStart = ""
    item.saleEnd = ""
    item.sevenDays = ""
    item.fifteenDays = ""
    item.thirtyDays = ""
    item.id = ""
    item.itemVendorList.clear()
}
class InvalidItemException(message: String): Exception(message)