package lesson_5.DATA

import lesson_5.DAO.Manufacturer

class ManufacturerData {

    private val manufacturerList: List<Manufacturer> = listOf(
        Manufacturer(1, "Apple", 85),
        Manufacturer(2, "Samsung", 79),
        Manufacturer(3, "Xiaomi", 71),
        Manufacturer(4, "Huawei", 75)
    )

    fun getManufacturers(): List<Manufacturer> = manufacturerList
}