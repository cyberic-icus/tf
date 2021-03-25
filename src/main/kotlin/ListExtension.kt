import java.lang.Exception

class BlankFoundException(message: String = "") : Exception("Found Blank String!\n$message")

fun List<String>.findBlank(): List<String>  {
    val a :MutableList<String> = mutableListOf<String>()
    for (i in this.indices) if (this[i] == "") throw BlankFoundException()
    else a.add(this[i]+"0")
    return a.toList()
}