package t1
import kotlinx.coroutines.*

fun String.concat(str: String) = "$this\n$str"

suspend fun getTime(): String = TimeService().getTime()
suspend fun getWeather(): String = WeatherService().getExtraInfo()

fun main() {
    var time = ""
    var weather = ""
    runBlocking {
        launch { time = getTime() }
        launch { weather = getWeather() }
    }
    println(time.concat(weather))
}


