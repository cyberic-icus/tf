class Stack(private val stack: MutableList<Any?>) {
    fun push(item: Any?) = stack.add(item)
    fun pop(): Any? {
        val element: Any? = stack.lastOrNull()
        stack.remove(element)
        return element
    }

}