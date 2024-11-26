package br.group.twenty.challenge.product.core.usecases

import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.core.gateways.IProductGateway

class CreateProductUseCase(private val gateway: IProductGateway) {
     fun execute(product: Product): Product {
        return gateway.createProduct(product)
    }
}