import java.lang.Exception

public class PC {
    fun isFull(capacity: Int):Boolean {
        if(capacity < 0) throw Exception("Good job!")
        return capacity >= 50
    }
    fun isLatest(year: Int): Boolean{
        if(year < 1990) throw Exception("Nah, ur PC is old.")
        return year == 2021
    }
}
