fun main(args: Array<String>) {
    println("======\nQueue example\n======")
    var queue1: Queue = Queue(mutableListOf(1, 2, 3, 4, 5))
    var queue2: Queue = Queue(mutableListOf("One", "Two"))

    println(queue1.dequeue())
    println(queue1.dequeue())

    println(queue2.dequeue())
    println(queue2.dequeue())

    queue2.enqueue("Hey")
    println(queue2.dequeue())
    println(queue2.dequeue()) // returns null

    println("======\nStack example\n======")
    var stack1: Stack = Stack(mutableListOf(1, 2, 3, 4))
    var stack2: Stack = Stack(mutableListOf('a','b','c'))

    println(stack1.pop()) // returns 4
    stack1.push(5)
    println(stack1.pop()) // returns 5

    println(stack2.pop()) // returns c
    stack2.push('z')
    println(stack2.pop()) // returns z
}