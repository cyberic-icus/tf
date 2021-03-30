package one

class Daemon : Thread() {
    init { this.isDaemon = true }
    override fun run() = println(getThreadInfo())

}