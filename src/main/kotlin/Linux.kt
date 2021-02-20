class Linux(override val name: String, override val kernel: String) : OS {
    private val site: String = "https://www.linuxfoundation.org/"
    val platform:String = "amd64"

    override fun start() {
        println("Sup, lets try to start up!")
        println("$name crashed.")
        println("You should try rewriting your processor driver, may be it'll help :)")
        println()
    }
    fun bash(){
        println("bash")
        println("\n")
        println("\n")
    }
}