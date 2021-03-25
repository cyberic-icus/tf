import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
class ProductDSLTest {

    @Test
    fun `dsl test`() {
        val product = mockk<Product> {
            every { id } returns 0
            every { name } returns "Test"
            every { price } returns 1000000.0
            every { sale(35.0) } returns 650000.0
        }

        assertAll("Testing Product",
            { assertEquals(0, product.id) },
            { assertEquals("Test", product.name) },
            { assertEquals(1000000.0, product.price) },
            { assertEquals(650000.0, product.sale(35.0)) }
        )
    }
}