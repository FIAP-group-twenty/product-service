package br.group.twenty.challenge.product.api.configs

import br.group.twenty.challenge.product.core.usecases.CreateProductUseCase
import br.group.twenty.challenge.product.core.usecases.DeleteProductUseCase
import br.group.twenty.challenge.product.core.usecases.GetProductByCategoryUseCase
import br.group.twenty.challenge.product.core.usecases.UpdateProductUseCase
import br.group.twenty.challenge.product.infrastructure.gateways.ProductGateway
import br.group.twenty.challenge.product.infrastructure.persistence.jpa.IProductDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration(
    val productDataSource: IProductDataSource,
) {

    @Bean
    fun productGateway(): ProductGateway {
        return ProductGateway(productDataSource)
    }

    @Bean
    fun createProductUseCase(): CreateProductUseCase {
        return CreateProductUseCase(productGateway())
    }

    @Bean
    fun deleteProductUseCase(): DeleteProductUseCase {
        return DeleteProductUseCase(productGateway())
    }

    @Bean
    fun getProductByCategoryUseCase(): GetProductByCategoryUseCase {
        return GetProductByCategoryUseCase(productGateway())
    }

    @Bean
    fun updateProductUseCase(): UpdateProductUseCase {
        return UpdateProductUseCase(productGateway())
    }

}