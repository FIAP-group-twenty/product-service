package core.usecases

import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.core.gateways.IProductGateway
import br.group.twenty.challenge.product.core.usecases.GetProductByCategoryUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GetProductByCategoryUseCaseTest {

    private val productGateway: IProductGateway = mockk()
    private val getProductByCategoryUseCase = GetProductByCategoryUseCase(productGateway)

    @Test
    fun `should get products by category successfully`() {
        val category = "Category A"
        val products = listOf(Product(id = 1, name = "Product A", category = category))
        every { productGateway.findProductByCategory(category) } returns products

        val result = getProductByCategoryUseCase.execute(category)

        assertEquals(products, result)
        verify(exactly = 1) { productGateway.findProductByCategory(category) }
    }

    @Test
    fun `should return empty list when no products found`() {
        val category = "Category B"
        val products = emptyList<Product>()
        every { productGateway.findProductByCategory(category) } returns products

        val result = getProductByCategoryUseCase.execute(category)

        assertTrue(result.isEmpty())
        verify(exactly = 1) { productGateway.findProductByCategory(category) }
    }
}
