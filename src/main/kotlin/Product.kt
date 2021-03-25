data class Product(var id: Int = 0, var name: String = "", var price: Double = 0.0) {
    fun sale(percent: Double): Double = price * (1 - percent / 100.0)
}

fun product(block: Product.() -> Unit): Product = Product().apply(block)