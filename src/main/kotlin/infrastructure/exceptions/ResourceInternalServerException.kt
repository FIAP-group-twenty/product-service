package br.group.twenty.challenge.product.infrastructure.exceptions

import br.group.twenty.challenge.product.core.entities.ErrorResponse

class ResourceInternalServerException(message: String = "An unexpected error occurred", exception: Exception? = null) :
    RuntimeException(message, exception) {

    fun formatter(): ErrorResponse = ErrorResponse(message = message, detail = cause?.cause?.message)

}