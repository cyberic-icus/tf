package one

class MyThread : Thread() {
    override fun run() = println(getThreadInfo())

}