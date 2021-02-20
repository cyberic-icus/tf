fun main() {
    val win = Windows("Windows 10", "Windows NT")
    win.info()
    win.start()
    win.hello()
    win.NewUpdate()

    val mac = MacOs("Catalina", "Darwin")
    mac.info()
    mac.start()
    mac.hello()
    mac.payToPlayMusic()

    val linux = Linux("Fedora", "Linux")
    linux.info()
    linux.start()
    linux.hello()
    linux.bash()
}