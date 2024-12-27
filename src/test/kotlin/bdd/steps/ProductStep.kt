package bdd.steps

import br.group.twenty.challenge.product.ProductApplication
import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.infrastructure.persistence.entities.ProductEntity
import br.group.twenty.challenge.product.infrastructure.persistence.jpa.IProductDataSource
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

@SpringBootTest(classes = [ProductApplication::class])
class ProductStep {

    @Autowired
    private lateinit var productRepository: IProductDataSource

    private lateinit var product: ProductEntity

    @Given("I have a product with name {string} and price {int}")
    fun i_have_a_product_with_name_and_price(name: String, price: BigDecimal) {
        product = ProductEntity(idProduct = 1, name = name, price = price, category = "BEBIDA", description = "coquinha")
    }

    @When("I create the product")
    fun i_create_the_product() {
        productRepository.save(product)
    }

    @Then("the product should be saved successfully")
    fun the_product_should_be_saved_successfully() {
        val savedProduct = productRepository.findById(product.idProduct!!)
        assertTrue(savedProduct.isPresent)
    }
}