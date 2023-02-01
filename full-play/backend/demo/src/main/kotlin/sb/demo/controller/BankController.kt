package sb.demo.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sb.demo.repository.entities.Bank
import sb.demo.service.BankService

@RestController
@RequestMapping("/bank")
class BankController(
    private val bankSvc: BankService
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun getAllBanks(): List<Bank> {
        return bankSvc.fetchBanks()
    }

    @GetMapping("/{bankId}")
    fun getBankById(@PathVariable bankId: String): String {
        bankSvc.saveBank()
        return "Temp $bankId"
    }

}