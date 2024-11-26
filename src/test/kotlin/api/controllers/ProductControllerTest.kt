import br.group.twenty.challenge.product.api.controllers.ProductController
import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.core.exceptions.ResourceNotFoundException
import br.group.twenty.challenge.product.core.usecases.CreateProductUseCase
import br.group.twenty.challenge.product.core.usecases.DeleteProductUseCase
import br.group.twenty.challenge.product.core.usecases.GetProductByCategoryUseCase
import br.group.twenty.challenge.product.core.usecases.UpdateProductUseCase
import br.group.twenty.challenge.product.infrastructure.exceptions.ResourceBadRequestException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ProductControllerTest {
    private val createProductUseCase: CreateProductUseCase = mockk()
    private val getProductByCategoryUseCase: GetProductByCategoryUseCase = mockk()
    private val updateProductUseCase: UpdateProductUseCase = mockk()
    private val deleteProductUseCase: DeleteProductUseCase = mockk()

    private val productController = ProductController(
        createProductUseCase, getProductByCategoryUseCase, updateProductUseCase, deleteProductUseCase
    )

    @Test
    fun `should create a product successfully`() {
        val product = Product(id = 1, name = "Product A", category = "Category A")
        every { createProductUseCase.execute(product) } returns product

        val response: ResponseEntity<Any> = productController.createProduct(product)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(product, response.body)
        verify(exactly = 1) { createProductUseCase.execute(product) }
    }

    @Test
    fun `should get products by category successfully`() {
        val products = listOf(Product(id = 1, name = "Product A", category = "Category A"))
        every { getProductByCategoryUseCase.execute("Category A") } returns products

        val response: ResponseEntity<List<Any>> = productController.getProductByCategory("Category A")

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(products, response.body)
        verify(exactly = 1) { getProductByCategoryUseCase.execute("Category A") }
    }

    @Test
    fun `should update a product successfully`() {
        val updatedProduct = Product(id = 1, name = "Updated Product", category = "Category A")
        every { updateProductUseCase.execute(1, updatedProduct) } returns updatedProduct

        val response: ResponseEntity<Any> = productController.updateProduct(1, updatedProduct)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(updatedProduct, response.body)
        verify(exactly = 1) { updateProductUseCase.execute(1, updatedProduct) }
    }

    @Test
    fun `should delete a product successfully`() {
        val product = Product(id = 1, name = "Updated Product", category = "Category A")
        every { deleteProductUseCase.execute(1) } returns product

        val response: ResponseEntity<Any> = productController.deleteProduct(1)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(
            product,
            response.body
        )
        verify(exactly = 1) { deleteProductUseCase.execute(1) }
    }

    @Test
    fun `should return empty list when no products found for category`() {
        val products = emptyList<Product>()
        every { getProductByCategoryUseCase.execute("NonExistentCategory") } returns products

        val response: ResponseEntity<List<Any>> = productController.getProductByCategory("NonExistentCategory")

        assertEquals(HttpStatus.OK, response.statusCode)
        assertTrue(response.body?.isEmpty() == true)
        verify(exactly = 1) { getProductByCategoryUseCase.execute("NonExistentCategory") }
    }

    @Test
    fun `should return bad request for invalid product data`() {
        val invalidProduct = Product(category = "")
        every { createProductUseCase.execute(invalidProduct) } throws ResourceBadRequestException("Name cannot be empty")

        val response: ResponseEntity<Any> = productController.createProduct(invalidProduct)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        assertTrue(response.body.toString().contains("Name cannot be empty"))
    }

    @Test
    fun `should return not found when product to update does not exist`() {
        val updatedProduct = Product(id = 1, name = "Updated Product", category = "Category A")
        every {
            updateProductUseCase.execute(1, updatedProduct)
        } throws ResourceNotFoundException(
            "Product not found"
        )

        val response: ResponseEntity<Any> = productController.updateProduct(1, updatedProduct)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertTrue(response.body.toString().contains("Product not found"))
        verify(exactly = 1) { updateProductUseCase.execute(1, updatedProduct) }
    }
}