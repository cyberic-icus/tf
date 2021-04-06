package t2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val iterator = generate().iterator()
        while (iterator.hasNext()) println(iterator.next())

    }
}

suspend fun CoroutineScope.generate() = produce{
    while (true) {
        delay(2000L)
        send(PasswordCreator().getPassword())
    }
}