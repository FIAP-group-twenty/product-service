package br.group.twenty.challenge.product.core.exceptions

import br.group.twenty.challenge.product.core.entities.ErrorResponse

class ResourceNotFoundException(message: String) : RuntimeException(message) {

    fun formatter(): ErrorResponse = ErrorResponse(message = message)

}