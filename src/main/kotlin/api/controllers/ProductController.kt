package br.group.twenty.challenge.product.api.controllers

import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.core.usecases.CreateProductUseCase
import br.group.twenty.challenge.product.core.usecases.DeleteProductUseCase
import br.group.twenty.challenge.product.core.usecases.GetProductByCategoryUseCase
import br.group.twenty.challenge.product.core.usecases.UpdateProductUseCase
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/products")
class ProductController(
    private val createProductUseCase: CreateProductUseCase,
    private val getProductByCategoryUseCase: GetProductByCategoryUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
) {

    @PostMapping
    fun createProduct(@RequestBody productRequest: Product): ResponseEntity<Any> =
        ResponseEntity.status(CREATED)
            .body(createProductUseCase.execute(productRequest))

    @GetMapping("/{category}")
    fun getProductByCategory(@PathVariable category: String): ResponseEntity<List<Any>> {
        return ResponseEntity.ok(getProductByCategoryUseCase.execute(category))
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Int, @RequestBody updatedProduct: Product): ResponseEntity<Any> {
        return ResponseEntity.ok(updateProductUseCase.execute(id, updatedProduct))
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Int): ResponseEntity<Any> {
        return ResponseEntity.ok().body(deleteProductUseCase.execute(id))
    }

}