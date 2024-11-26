package br.group.twenty.challenge.product.core.usecases

import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.core.gateways.IProductGateway

class UpdateProductUseCase(
    private val gateway: IProductGateway
) {
    fun execute(id: Int, product: Product): Product {
        gateway.findProductById(id).let { oldProduct ->
            return gateway.updateProduct(oldProduct, product)
        }
    }
}