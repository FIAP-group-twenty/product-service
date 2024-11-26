package br.group.twenty.challenge.product.core.gateways

import br.group.twenty.challenge.product.core.entities.Product

interface IProductGateway {
    fun createProduct(product: Product): Product
    fun deleteProduct(product: Product): Product
    fun findProductById(id: Int): Product
    fun findProductByCategory(category: String): List<Product>
    fun updateProduct(oldProduct: Product, product: Product): Product
}