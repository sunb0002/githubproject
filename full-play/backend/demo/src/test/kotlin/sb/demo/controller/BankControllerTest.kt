package sb.demo.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objMapper: ObjectMapper
) {
    private val baseUrl = "/bank"

    @Nested
    @DisplayName("BankController test suite - GET")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTests {
        @Test
        @DisplayName("get one bank")
        fun getOneBank() {
        }

    }


}