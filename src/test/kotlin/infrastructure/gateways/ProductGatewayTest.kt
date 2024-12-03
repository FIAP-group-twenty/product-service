package infrastructure.gateways

import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.core.exceptions.ResourceNotFoundException
import br.group.twenty.challenge.product.infrastructure.gateways.ProductGateway
import br.group.twenty.challenge.product.infrastructure.persistence.entities.ProductEntity
import br.group.twenty.challenge.product.infrastructure.persistence.jpa.IProductDataSource
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class ProductGatewayTest {

    private val productDataSource: IProductDataSource = mockk()
    private val productGateway = ProductGateway(productDataSource)

    private val product = Product(
        name = "Sample Product",
        price = BigDecimal(100.0),
        description = "A sample product",
        category = "Sample Category"
    )

    private val productEntity = ProductEntity(
        idProduct = 1,
        name = product.name,
        price = product.price,
        description = product.description,
        category = product.category
    )

    @Test
    fun `createProduct should save product and return created product`() {
        every { productDataSource.save(any<ProductEntity>()) } returns productEntity

        val result = productGateway.createProduct(product)

        assertEquals(product.name, result.name)
        assertEquals(product.category, result.category)
        verify(exactly = 1) { productDataSource.save(any<ProductEntity>()) }
    }

//    @Test
//    fun `createProduct should throw ResourceInternalServerException on failure`() {
//        every { productDataSource.save(any<ProductEntity>()) } throws RuntimeException("DB error")
//
//        val exception = assertThrows<ResourceInternalServerException> {
//            productGateway.createProduct(product)
//        }
//
//        assertTrue(exception.message?.contains("Failed to create product") == true)
//        verify(exactly = 1) { productDataSource.save(any<ProductEntity>()) }
//    }

    @Test
    fun `deleteProduct should delete product successfully`() {
        every { productDataSource.delete(any<ProductEntity>()) } just Runs

        val result = productGateway.deleteProduct(product)

        assertEquals(product.name, result.name)
        verify(exactly = 1) { productDataSource.delete(any<ProductEntity>()) }
    }

//    @Test
//    fun `deleteProduct should throw ResourceInternalServerException on failure`() {
//        every { productDataSource.delete(any<ProductEntity>()) } throws RuntimeException("DB error")
//
//        val exception = assertThrows<ResourceInternalServerException> {
//            productGateway.deleteProduct(product)
//        }
//
//        assertTrue(exception.message?.contains("Failed to delete product") == true)
//        verify(exactly = 1) { productDataSource.delete(any<ProductEntity>()) }
//    }

    @Test
    fun `findProductById should return product when found`() {
        every { productDataSource.findByIdProduct(1) } returns productEntity

        val result = productGateway.findProductById(1)

        assertEquals(product.name, result.name)
        verify(exactly = 1) { productDataSource.findByIdProduct(1) }
    }

    @Test
    fun `findProductById should throw ResourceNotFoundException when product not found`() {
        every { productDataSource.findByIdProduct(1) } returns null

        val exception = assertThrows<ResourceNotFoundException> {
            productGateway.findProductById(1)
        }

        assertTrue(exception.message?.contains("Product not found") == true)
        verify(exactly = 1) { productDataSource.findByIdProduct(1) }
    }

    @Test
    fun `findProductByCategory should return list of products`() {
        every { productDataSource.findByCategory("Sample Category") } returns listOf(productEntity)

        val result = productGateway.findProductByCategory("Sample Category")

        assertEquals(1, result.size)
        assertEquals(product.name, result.first().name)
        verify(exactly = 1) { productDataSource.findByCategory("Sample Category") }
    }

//    @Test
//    fun `findProductByCategory should throw ResourceInternalServerException on failure`() {
//        every { productDataSource.findByCategory("Sample Category") } throws RuntimeException("DB error")
//
//        val exception = assertThrows<ResourceInternalServerException> {
//            productGateway.findProductByCategory("Sample Category")
//        }
//
//        assertTrue(exception.message?.contains("Failed to find product with category") == true)
//        verify(exactly = 1) { productDataSource.findByCategory("Sample Category") }
//    }

    @Test
    fun `updateProduct should update and return updated product`() {
        val updatedProduct = product.copy(name = "Updated Product")
        val updatedEntity = productEntity.copy(name = "Updated Product")
        every { productDataSource.save(any<ProductEntity>()) } returns updatedEntity

        val result = productGateway.updateProduct(product, updatedProduct)

        assertEquals("Updated Product", result.name)
        verify(exactly = 1) { productDataSource.save(any<ProductEntity>()) }
    }

//    @Test
//    fun `updateProduct should throw ResourceInternalServerException on failure`() {
//        every { productDataSource.save(any<ProductEntity>()) } throws RuntimeException("DB error")
//
//        val exception = assertThrows<ResourceInternalServerException> {
//            productGateway.updateProduct(product, product)
//        }
//
//        assertTrue(exception.message?.contains("Failed to update product") == true)
//        verify(exactly = 1) { productDataSource.save(any<ProductEntity>()) }
//    }

}