import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ListExtensionTest {
    @Test
    fun `test findBlank ok`() {
        mockkStatic("ListExtensionKt")
        every { listOf("12","f0").findBlank() }
            .returns(listOf("120","f"))

        val expected = listOf("120","f0")
        val result = listOf("12","f").findBlank()

        Assertions.assertEquals(expected.equals(result), true)
        verify { listOf("12","f").findBlank() }
    }

    @Test
    fun `test findBlank exception`() {
        mockkStatic("ListExtensionKt")
        every { listOf("12","","f").findBlank() } throws BlankFoundException()

        assertThrows<BlankFoundException> { listOf("12","","f").findBlank() }
        verify { listOf("12","","f").findBlank() }
    }
}