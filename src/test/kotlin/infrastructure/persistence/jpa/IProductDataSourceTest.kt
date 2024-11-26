package infrastructure.persistence.jpa

import api.configs.TestConfig
import br.group.twenty.challenge.product.infrastructure.persistence.entities.ProductEntity
import br.group.twenty.challenge.product.infrastructure.persistence.jpa.IProductDataSource
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import java.math.BigDecimal

@DataJpaTest
@ContextConfiguration(classes = [TestConfig::class])
class IProductDataSourceTest {

    @Autowired
    private lateinit var productDataSource: IProductDataSource

    @Test
    fun `should find product by ID`() {
        val product = ProductEntity(
            name = "Test Product",
            price = BigDecimal("29.99"),
            description = "Test Description",
            category = "Test Category"
        )
        val savedProduct = productDataSource.save(product)

        val foundProduct = productDataSource.findByIdProduct(savedProduct.idProduct!!)

        assertNotNull(foundProduct)
        assertEquals(savedProduct.idProduct, foundProduct?.idProduct)
        assertEquals("Test Product", foundProduct?.name)
    }

    @Test
    fun `should return null when product ID does not exist`() {
        val foundProduct = productDataSource.findByIdProduct(999)

        assertNull(foundProduct)
    }

    @Test
    fun `should find products by category`() {
        val product1 = ProductEntity(
            name = "Product 1",
            price = BigDecimal("19.99"),
            description = "Description 1",
            category = "Category A"
        )
        val product2 = ProductEntity(
            name = "Product 2",
            price = BigDecimal("29.99"),
            description = "Description 2",
            category = "Category A"
        )
        val product3 = ProductEntity(
            name = "Product 3",
            price = BigDecimal("39.99"),
            description = "Description 3",
            category = "Category B"
        )
        productDataSource.saveAll(listOf(product1, product2, product3))

        val products = productDataSource.findByCategory("Category A")

        assertEquals(2, products.size)
        assertTrue(products.any { products[0].name == "Product 1" })
        assertTrue(products.any { products[1].name == "Product 2" })
    }

    @Test
    fun `should return empty list when no products in category`() {
        val products = productDataSource.findByCategory("Nonexistent Category")
        assertTrue(products.isEmpty())
    }
}
