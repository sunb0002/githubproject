package sb.demo.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sb.demo.model.BankUserDTO

@RestController
@RequestMapping("/basic")
class BasicController {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    // [Basic] Kotlin inline function.
    @GetMapping("a")
    fun api1() = "Hello1 - this returns a raw string!";

    // [Logger] Lombok @Slf4j is too troublesome to setup and doesn't work well with Kotlin.
    // [@JsonProperty] rename json field.
    @GetMapping("b")
    fun api2(): BankUserDTO {
        logger.info("Hi {} Log{}", "Info", 233)
        return BankUserDTO("zhong li", 5000, "liyue")
    }


}