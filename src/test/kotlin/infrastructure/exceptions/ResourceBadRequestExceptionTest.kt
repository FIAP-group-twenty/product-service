package infrastructure.exceptions

import br.group.twenty.challenge.product.core.exceptions.ResourceNotFoundException
import br.group.twenty.challenge.product.infrastructure.exceptions.ResourceBadRequestException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ResourceBadRequestExceptionTest {

    @Test
    fun `should throw ResourceBadRequestException with correct message`() {
        val exception = assertThrows<ResourceBadRequestException> {
            throw ResourceBadRequestException("Resource bad request")
        }
        assertEquals("Resource bad request", exception.message)
    }

    @Test
    fun `should throw ResourceBadRequestException with correct message and exception`() {
        val myException = Exception("Testing an exception")
        val exception = assertThrows<ResourceBadRequestException> {
            throw ResourceBadRequestException("Resource bad request", myException)
        }
        assertEquals("Resource bad request", exception.message)
        assertEquals(myException, exception.cause)
        assertEquals(myException.message, exception.cause?.message)
    }

}