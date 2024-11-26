package core.exceptions

import br.group.twenty.challenge.product.core.exceptions.ResourceNotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ResourceNotFoundExceptionTest {

    @Test
    fun `should throw ResourceNotFoundException with correct message`() {
        val exception = assertThrows<ResourceNotFoundException> {
            throw ResourceNotFoundException("Resource not found")
        }
        assertEquals("Resource not found", exception.message)
    }
}