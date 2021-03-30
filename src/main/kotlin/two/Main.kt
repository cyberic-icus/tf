package two

import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.thread

fun main() {
    val list = AtomicReference<BoolList>(BoolList())
    val t = thread {
        repeat(100) {
            list.get().add()
            Thread.sleep(25)
        }
    }

    ObserverThread(list.get(), t,  10).apply {
        start()
    }
    ObserverThread(list.get(), t,  50).apply {
        start()
    }
    ObserverThread(list.get(), t,  100).apply {
        start()
    }

}


