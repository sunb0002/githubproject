package sb.demo.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sb.demo.model.BankDTO
import sb.demo.service.BankService

@RestController
@RequestMapping("/bank")
class BankController(
    private val bankSvc: BankService
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun getAllBanks(): List<BankDTO> {
        return bankSvc.fetchBanks().map { BankDTO(it) } // Or put the mapping logic in entity class then { ::toDTO }
    }

    @GetMapping("/{bankId}")
    fun getBankById(@PathVariable bankId: String): String {
        val dto = bankSvc.saveBank()
        return "Done $bankId ---- $dto"
    }

}