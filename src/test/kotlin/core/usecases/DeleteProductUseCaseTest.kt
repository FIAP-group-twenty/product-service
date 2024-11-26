package core.usecases

import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.core.exceptions.ResourceNotFoundException
import br.group.twenty.challenge.product.core.gateways.IProductGateway
import br.group.twenty.challenge.product.core.usecases.DeleteProductUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeleteProductUseCaseTest {

    private val productGateway: IProductGateway = mockk()
    private val deleteProductUseCase = DeleteProductUseCase(productGateway)

    @Test
    fun `should delete product successfully`() {
        val product = Product(id = 1, name = "New Product", category = "Category A")
        every { productGateway.findProductById(1) } returns product
        every { productGateway.deleteProduct(product) } returns product

        val result = deleteProductUseCase.execute(1)

        assertTrue(result == product)
        verify(exactly = 1) { productGateway.deleteProduct(product) }
    }

    @Test
    fun `should throw exception when product not found`() {
        val product = Product(id = 1, name = "New Product", category = "Category A")
        every { productGateway.findProductById(1) } throws ResourceNotFoundException("Product not found")
        every { productGateway.deleteProduct(product) } throws ResourceNotFoundException("Product not found")

        val exception = assertThrows<ResourceNotFoundException> {
            deleteProductUseCase.execute(1)
        }

        assertEquals("Product not found", exception.message)
    }

}
