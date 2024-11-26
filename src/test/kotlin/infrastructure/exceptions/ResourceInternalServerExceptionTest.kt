package infrastructure.exceptions

import br.group.twenty.challenge.product.infrastructure.exceptions.ResourceBadRequestException
import br.group.twenty.challenge.product.infrastructure.exceptions.ResourceInternalServerException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ResourceInternalServerExceptionTest {

    @Test
    fun `should throw ResourceInternalServerException with default message`() {
        val exception = assertThrows<ResourceInternalServerException> {
            throw ResourceInternalServerException()
        }
        assertEquals("An unexpected error occurred", exception.message)
    }

    @Test
    fun `should throw ResourceInternalServerException with correct message`() {
        val exception = assertThrows<ResourceInternalServerException> {
            throw ResourceInternalServerException("Resource internal serve")
        }
        assertEquals("Resource internal serve", exception.message)
    }

    @Test
    fun `should throw ResourceInternalServerException with correct message and exception`() {
        val myException = Exception("Testing an ResourceInternalServerException")
        val exception = assertThrows<ResourceInternalServerException> {
            throw ResourceInternalServerException("Resource internal server", myException)
        }
        assertEquals("Resource internal server", exception.message)
        assertEquals(myException, exception.cause)
        assertEquals(myException.message, exception.cause?.message)
    }

}