class Windows(override val name: String, override val kernel: String) : OS {
    private val father: String = "MS-DOS"
    val api:String = "Windows API"

    override fun start(){
        println("Starting $name.")
        for (i:Int in 0..100){
            println("$i% loaded.")
        }
        println("$name started!")
    }
    fun NewUpdate(){
        println("Found new update! Come back later(approx. 10 hours) :)")
        println("\n")
        println("\n")
    }
}