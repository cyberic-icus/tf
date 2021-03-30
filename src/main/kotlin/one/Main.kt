package one

import java.lang.StringBuilder
import kotlin.concurrent.thread

fun getThreadInfo(): String {
    val cur = Thread.currentThread()
    return StringBuilder().apply {
        appendln("name: ${cur.name}")
        appendln("id: ${cur.id}")
        appendln("daemon: ${cur.isDaemon}")
        appendln("pr: ${cur.priority}")
    }.toString()
}

fun main(args: Array<String>) {
    MyThread().apply { start() }.join()

    Thread(MyRunnable()).apply { start() }.join()

    thread { println(getThreadInfo()) }.join()

    Daemon().apply { start() }.join()

    thread(priority = Thread.MAX_PRIORITY) { println(getThreadInfo()) }.join()
}