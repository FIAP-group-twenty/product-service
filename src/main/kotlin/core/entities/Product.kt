package br.group.twenty.challenge.product.core.entities

import java.math.BigDecimal

data class Product(
    val id: Int? = null,
    val name: String? = null,
    val category: String? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    val description: String? = null
)