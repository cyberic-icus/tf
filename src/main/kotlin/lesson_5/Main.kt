package lesson_5
import lesson_5.DATA.CustomerData
import lesson_5.DATA.OrderData
import lesson_5.DATA.ManufacturerData
import lesson_5.DATA.ProductData
import lesson_5.DB.*

fun oneToMany() {
    val manufacturer = Table("DB_1", "Producers", producerArgs, producerArgsMap)
    val products = Table("DB_1", "Products", productArgs, productArgsMap)

    val db = DataBaseInit("DB_1", listOf(manufacturer, products))
    val manufacturerQ = DataBaseQuery(manufacturer)
    val productQ = DataBaseQuery(products)

    db.createAllTables()

    manufacturerQ.insertList(ManufacturerData().getManufacturers())


    productQ.insertList(ProductData().getProducts())

    manufacturerQ.getProductsByProducerID(1, products).forEach { println(it) }

    println(manufacturerQ.selectByID(2))

    productQ.selectUnderCost(80).forEach { println(it) }

    manufacturerQ.join(products).forEach { println(it) }

    db.dropAllTables()
}

fun manyToMany() {
    val products = Table("DB_2", "Products", productArgs, productArgsMap)
    val orders = Table("DB_2", "Orders", orderArgs, orderArgsMap)
    val customers = Table("DB_2", "Customers", customerArgs, customerArgsMap)

    val dbInit = DataBaseInit("DB_2", listOf(products, orders, customers))

    val prodQ = DataBaseQuery(products)
    val ordQ = DataBaseQuery(orders)
    val custQ = DataBaseQuery(customers)

    dbInit.createAllTables()

    prodQ.insertList(ProductData().getProducts())

    ordQ.insertList(OrderData().getOrders())

    custQ.insertList(CustomerData().getCustomers())

    prodQ.getCustomersByProductID(1, orders, customers).forEach { println(it) }

    prodQ.getProductsByCustomerID(1, orders, products).forEach { println(it) }

    ordQ.groupCustomersByProductsID().forEach { println(it) }

    prodQ.selectAndSortProducts(80).forEach { println(it) }

    dbInit.dropAllTables()
}

fun main() {
    oneToMany()
    println()
    manyToMany()
}