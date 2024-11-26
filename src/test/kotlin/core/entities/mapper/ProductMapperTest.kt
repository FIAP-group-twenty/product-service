package core.entities.mapper

import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.core.entities.mapper.ProductMapper.toDto
import br.group.twenty.challenge.product.core.entities.mapper.ProductMapper.toEntity
import br.group.twenty.challenge.product.core.entities.mapper.ProductMapper.updateProduct
import br.group.twenty.challenge.product.infrastructure.persistence.entities.ProductEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ProductMapperTest {

    @Test
    fun `should map Product to ProductEntity successfully`() {
        val product = Product(
            id = 1,
            name = "Product A",
            category = "Category A",
            price = BigDecimal(3),
            description = "Description of product"
        )
        val productEntity = product.toEntity()

        assertNotNull(productEntity)
        assertEquals(product.id, productEntity.idProduct)
        assertEquals(product.name, productEntity.name)
        assertEquals(product.category, productEntity.category)
        assertEquals(product.price, productEntity.price)
        assertEquals(product.description, productEntity.description)
    }

    @Test
    fun `should map ProductEntity to Product successfully`() {
        val productEntity = ProductEntity(
            idProduct = 1,
            name = "Product A",
            category = "Category A",
            price = BigDecimal(3),
            description = "Description of product"
        )
        val product = productEntity.toDto()

        assertNotNull(product)
        assertEquals(productEntity.idProduct, product.id)
        assertEquals(productEntity.name, product.name)
        assertEquals(productEntity.category, product.category)
        assertEquals(productEntity.price, product.price)
        assertEquals(productEntity.description, product.description)
    }

    @Test
    fun `should map update product successfully`() {
        val oldProduct = Product(
            id = 1,
            name = "Product A",
            category = "Category A",
            price = BigDecimal(3),
            description = "Description of product"
        )

        val product = Product(
            id = 1,
            name = "Product B",
            category = "Category B",
            price = BigDecimal(5),
            description = "Description of product"
        )
        val newProduct = oldProduct.updateProduct(product)

        assertNotNull(newProduct)
        assertEquals(product.id, newProduct.idProduct)
        assertEquals(product.name, newProduct.name)
        assertEquals(product.category, newProduct.category)
        assertEquals(product.price, newProduct.price)
        assertEquals(product.description, newProduct.description)
    }
}