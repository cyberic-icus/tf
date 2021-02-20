interface OS {
    val name: String
    val kernel: String
    fun start()
    fun hello(){ println("Hello from $name!")}
    fun info(){ println("INFO:\n Name: $name\n Kernel: $kernel")}
}