package t1

import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class TimeNow {
    private val weatherOptions = listOf("Clear sky", "Clouds", "Raining")
    val timeNow: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
    val weather: String = weatherOptions[Random.nextInt(0, 2)]
    val pressure: Int = Random.nextInt(750, 760)
}

class TimeService {
    suspend fun getTime(): String {
        val time = TimeNow()
        delay(500)
        return time.timeNow
    }
}

class WeatherService {
    suspend fun getExtraInfo(): String {
        val time = TimeNow()
        delay(500)
        return "Current weather:${time.weather} \nCurrent atmospheric pressure: ${time.pressure} Pas"
    }
}