package lesson_5.DB

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

class DataBaseSevice(private val databaseName: String) : AutoCloseable {

    val conn: Connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/$databaseName")
    private val stm: Statement = conn.createStatement()

    override fun close() {
        conn.close()
        stm.close()
    }
}