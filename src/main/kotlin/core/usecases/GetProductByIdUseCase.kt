package br.group.twenty.challenge.product.core.usecases

import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.core.gateways.IProductGateway

class GetProductByIdUseCase(
    private val gateway: IProductGateway
) {

    fun execute(id: Int): Product {
        return gateway.findProductById(id)
    }
}