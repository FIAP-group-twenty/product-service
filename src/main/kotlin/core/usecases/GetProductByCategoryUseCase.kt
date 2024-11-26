package br.group.twenty.challenge.product.core.usecases

import br.group.twenty.challenge.product.core.entities.Product
import br.group.twenty.challenge.product.core.gateways.IProductGateway

class GetProductByCategoryUseCase(private val gateway: IProductGateway) {
    fun execute(category: String): List<Product> {
        return gateway.findProductByCategory(category)
    }
}