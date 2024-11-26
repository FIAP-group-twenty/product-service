package core.entities

import br.group.twenty.challenge.product.core.entities.Product
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ProductTest {

    @Test
    fun `should create product entity with correct attributes`() {
        val product = Product(id = 1, name = "Product A", category = "Category A")

        assertNotNull(product)
        assertEquals(1, product.id)
        assertEquals("Product A", product.name)
        assertEquals("Category A", product.category)
    }
}