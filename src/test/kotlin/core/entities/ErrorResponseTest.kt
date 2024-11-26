package core.entities

import br.group.twenty.challenge.product.core.entities.ErrorResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ErrorResponseTest {

    @Test
    fun `should create error response with correct attributes`() {
        val errorResponse = ErrorResponse("Error", "Some error occurred")

        assertNotNull(errorResponse)
        assertEquals("Error", errorResponse.message)
        assertEquals("Some error occurred", errorResponse.detail)
    }
}