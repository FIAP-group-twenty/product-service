package core.usecases

import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.core.gateways.IProductGateway
import br.group.twenty.challenge.product.core.usecases.CreateProductUseCase
import br.group.twenty.challenge.product.infrastructure.exceptions.ResourceBadRequestException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class CreateProductUseCaseTest {

    private val productGateway: IProductGateway = mockk()
    private val createProductUseCase = CreateProductUseCase(productGateway)

    @Test
    fun `should create product successfully`() {
        val product = Product(id = 1, name = "New Product", category = "Category A")
        every { productGateway.createProduct(any()) } returns product

        val result = createProductUseCase.execute(product)

        assertEquals(product, result)
        verify(exactly = 1) { productGateway.createProduct(product) }
    }

    @Test
    fun `should throw exception when product name is invalid`() {
        val invalidProduct = Product(category = "")

        every { productGateway.createProduct(any()) } throws ResourceBadRequestException("Name cannot be empty")

        val exception = assertThrows<ResourceBadRequestException> {
            createProductUseCase.execute(invalidProduct)
        }

        assertEquals("Name cannot be empty", exception.message)
    }

    @Test
    fun `should throw exception when product category is invalid`() {
        val invalidProduct = Product(name = "Apple")

        every { productGateway.createProduct(any()) } throws ResourceBadRequestException("Category cannot be empty")

        val exception = assertThrows<ResourceBadRequestException> {
            createProductUseCase.execute(invalidProduct)
        }

        assertEquals("Category cannot be empty", exception.message)
    }

    @Test
    fun `should throw exception when product description is invalid`() {
        val invalidProduct = Product(name = "Apple", category = "Fruit")

        every { productGateway.createProduct(any()) } throws ResourceBadRequestException("Description cannot be empty")

        val exception = assertThrows<ResourceBadRequestException> {
            createProductUseCase.execute(invalidProduct)
        }

        assertEquals("Description cannot be empty", exception.message)
    }

    @Test
    fun `should throw exception when product price is invalid`() {
        val invalidProduct = Product(name = "Apple", category = "Fruit", description = "Big apple", price = BigDecimal.ZERO)

        every { productGateway.createProduct(any()) } throws ResourceBadRequestException("Price cannot be 0")

        val exception = assertThrows<ResourceBadRequestException> {
            createProductUseCase.execute(invalidProduct)
        }

        assertEquals("Price cannot be 0", exception.message)
    }
}
