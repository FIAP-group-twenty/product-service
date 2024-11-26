package br.group.twenty.challenge.product.core.entities

data class ErrorResponse(
    val message: String?,
    val detail: String? = null
)