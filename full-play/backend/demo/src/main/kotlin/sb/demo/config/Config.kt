package sb.demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.bind.annotation.ControllerAdvice

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
class DBConfig

@ControllerAdvice
class ExceptionHandler
