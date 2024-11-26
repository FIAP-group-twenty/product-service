package br.group.twenty.challenge.product.infrastructure.exceptions

import br.group.twenty.challenge.product.core.entities.ErrorResponse

class ResourceBadRequestException(message: String, exception: Exception? = null) :
    RuntimeException(message, exception) {

    fun formatter(): ErrorResponse = ErrorResponse(message = message, detail = cause?.message)

}