package infrastructure.persistence.entities

import br.group.twenty.challenge.product.infrastructure.exceptions.ResourceBadRequestException
import br.group.twenty.challenge.product.infrastructure.persistence.entities.ProductEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class ProductEntityTest {

    @Test
    fun `should create a valid ProductEntity`() {
        val product = ProductEntity(
            name = "Valid Product",
            price = BigDecimal("19.99"),
            description = "A valid description",
            category = "Category A"
        )

        assertNotNull(product)
        assertEquals("Valid Product", product.name)
        assertEquals(BigDecimal("19.99"), product.price)
        assertEquals("A valid description", product.description)
        assertEquals("Category A", product.category)
    }

    @Test
    fun `should throw exception when name is null`() {
        val exception = assertThrows<ResourceBadRequestException> {
            ProductEntity(
                name = null,
                price = BigDecimal("19.99"),
                description = "Valid description",
                category = "Category A"
            )
        }
        assertEquals("Name cannot be empty", exception.message)
    }

    @Test
    fun `should throw exception when name is blank`() {
        val exception = assertThrows<ResourceBadRequestException> {
            ProductEntity(
                name = "  ",
                price = BigDecimal("19.99"),
                description = "Valid description",
                category = "Category A"
            )
        }
        assertEquals("Name cannot be empty", exception.message)
    }

    @Test
    fun `should throw exception when category is null`() {
        val exception = assertThrows<ResourceBadRequestException> {
            ProductEntity(
                name = "Valid Product",
                price = BigDecimal("19.99"),
                description = "Valid description",
                category = null
            )
        }
        assertEquals("Category cannot be empty", exception.message)
    }

    @Test
    fun `should throw exception when category is blank`() {
        val exception = assertThrows<ResourceBadRequestException> {
            ProductEntity(
                name = "Valid Product",
                price = BigDecimal("19.99"),
                description = "Valid description",
                category = "   "
            )
        }
        assertEquals("Category cannot be empty", exception.message)
    }

    @Test
    fun `should throw exception when description is null`() {
        val exception = assertThrows<ResourceBadRequestException> {
            ProductEntity(
                name = "Valid Product",
                price = BigDecimal("19.99"),
                description = null,
                category = "Category A"
            )
        }
        assertEquals("Description cannot be empty", exception.message)
    }

    @Test
    fun `should throw exception when description is blank`() {
        val exception = assertThrows<ResourceBadRequestException> {
            ProductEntity(
                name = "Valid Product",
                price = BigDecimal("19.99"),
                description = "   ",
                category = "Category A"
            )
        }
        assertEquals("Description cannot be empty", exception.message)
    }

    @Test
    fun `should throw exception when price is zero`() {
        val exception = assertThrows<ResourceBadRequestException> {
            ProductEntity(
                name = "Valid Product",
                price = BigDecimal.ZERO,
                description = "Valid description",
                category = "Category A"
            )
        }
        assertEquals("Price cannot be 0", exception.message)
    }

    @Test
    fun `should throw exception when price is negative`() {
        val exception = assertThrows<ResourceBadRequestException> {
            ProductEntity(
                name = "Valid Product",
                price = BigDecimal("-1.00"),
                description = "Valid description",
                category = "Category A"
            )
        }
        assertEquals("Price cannot be 0", exception.message)
    }
}
