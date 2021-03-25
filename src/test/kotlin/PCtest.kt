import io.mockk.junit5.MockKExtension
import io.mockk.spyk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@ExtendWith(MockKExtension::class)
class PCtest {

    @ParameterizedTest
    @CsvSource("50, true","70, true","100, true")
    fun `test isFull true`(a: Int, expected: Boolean) {
        val func = spyk<PC>()
        val result = func.isFull(a)

        assertEquals(expected, result)
    }
    @ParameterizedTest
    @CsvSource("1, false","5, false","49, false")
    fun `test isFull false`(a: Int, expected: Boolean) {
        val func = spyk<PC>()
        val result = func.isFull(a)

        assertEquals(expected, result)
    }
    @ParameterizedTest
    @CsvSource("2021, true")
    fun `test isLatest true`(a: Int, expected: Boolean) {
        val func = spyk<PC>()
        val result = func.isLatest(a)

        assertEquals(expected, result)
    }
    @ParameterizedTest
    @CsvSource("2011, false","2015, false","2000, false")
    fun `test isLatest false`(a: Int, expected: Boolean) {
        val func = spyk<PC>()
        val result = func.isLatest(a)

        assertEquals(expected, result)
    }

}
