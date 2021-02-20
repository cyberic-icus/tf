class MacOs(override val name: String, override val kernel: String) : OS {
    private val cost: Int = 250000
    private var balance:Double = 16.98
    val osFamily: String = "Unix"


    override fun start() {
        println("Trying to start $name!")
        println("Your $cost\$ device with $name started... painfully...")
        println()
    }

    override fun hello() {
        println("Hello, dude!")
        println()
    }
    fun payToPlayMusic(){
        println("Your balance: $balance")
        println("Transaction complete! You can listen music for 20 mins.")
        balance -= 15
        println("Your balance now: $balance")
        println("\n")
        println("\n")
    }
}