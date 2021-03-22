package lesson_5.DAO

data class Manufacturer(val producerID: Int, val producerName: String, val rating: Int):DAO {
    override fun toList(): List<Any> = listOf(producerID, producerName, rating)

    override fun toValueString(): String = "$producerID, \"$producerName\", $rating"
}