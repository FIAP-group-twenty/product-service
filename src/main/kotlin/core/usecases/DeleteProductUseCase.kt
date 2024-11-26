package br.group.twenty.challenge.product.core.usecases

import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.core.gateways.IProductGateway

class DeleteProductUseCase(
    private val gateway: IProductGateway
) {
    fun execute(id: Int): Product {
        gateway.findProductById(id).let { product ->
            return gateway.deleteProduct(product)
        }
    }
}