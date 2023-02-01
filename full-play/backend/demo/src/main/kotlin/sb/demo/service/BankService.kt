package sb.demo.service

import org.springframework.stereotype.Service
import sb.demo.repository.BankRepository
import sb.demo.repository.UserRepository
import sb.demo.repository.entities.Bank
import sb.demo.repository.entities.BankUser

@Service
class BankService(
    private val bankRepo: BankRepository,
    private val userRepo: UserRepository
) {
    // @Autowired
    // private lateinit var bankRepo: BankRepository // Rarely used after Spring5. Auto-injection from constructor.
    fun fetchBanks(): List<Bank> {
        return bankRepo.findAllByFeeGreaterThan(10)
    }

    fun saveBank() {
        // Must save the transient instance "user1" before flushing
        val user1 = userRepo.save(BankUser("Lebron", 38, "LA"))
        val bank1 = Bank(bankName = "scb", users = mutableListOf(user1))
        val newBank = bankRepo.save(bank1)
        println("Saved bank: $newBank")
    }
}