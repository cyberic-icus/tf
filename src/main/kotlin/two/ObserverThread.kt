package two

class ObserverThread(private val list: BoolList, private val t: Thread, private val int: Long) : Thread() {
    @Synchronized
    override fun run() {
        while (t.isAlive) {
            println("From ${currentThread().name} ${list.count()}")
            sleep(int)
        }
    }
}