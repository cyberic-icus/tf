class Queue(private val queue: MutableList<Any?>) {
    fun enqueue(item : Any?) = queue.add(item)

    fun dequeue(): Any? {
        val element: Any? = queue.firstOrNull()
        queue.remove(element)
        return element
    }
}