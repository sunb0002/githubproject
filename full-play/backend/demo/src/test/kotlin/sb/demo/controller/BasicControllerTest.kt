package sb.demo.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.util.AssertionErrors.assertTrue
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class BasicControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    @DisplayName("sb3 test")
    fun sb3() {
        val resp: MvcResult = mockMvc.get("/basic/sb3")
            .andDo { println("Calling sb3 from test") }
            .andExpect {
                status {
                    isOk()
                    is2xxSuccessful()
                }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.bankId") {
                    exists()
                    value("scb01")
                }
            }
            .also { println("Response is: $it") } // Resp details are already logged, visible if tests failed.
            .andReturn()
        assertTrue("Response should has fee field", resp.response.contentAsString.contains("fee"))
    }

}