package sb.demo.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sb.demo.model.BankDTO
import sb.demo.model.BankOne

@RestController
@RequestMapping("/basic")
class BasicController {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    // Basic, but with inline function
    @GetMapping("hello1")
    fun hello1() = "Hello1 - this returns a raw string!";

    // Logger. Lombok @Slf4j is too troublesome to setup and doesn't work well with Kotlin
    @GetMapping("hello2")
    fun hello2(): String {
        logger.info("Hi {} Log{}", "Info", 2)
        val bank = BankOne("id", 22)
        return "Hello2 $bank.bankId"
    }

    // DTO.
    @GetMapping("hello3")
    fun hello3() = BankDTO("scb01", 20)

}