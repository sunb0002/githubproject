package sb.demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import sb.demo.model.GeneralError

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
class DBConfig

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun globalHandler(ex: Exception, request: WebRequest): GeneralError {
        println("Exception occurred ---- ${ex.message}, $request")
        return GeneralError(ex.message ?: "UNKNOWN_ERROR")
    }
}
