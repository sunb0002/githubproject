package sb.demo.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sb.demo.model.BankDTO
import sb.demo.repository.AccountRepository
import sb.demo.repository.BankRepository
import sb.demo.repository.UserRepository
import sb.demo.repository.entities.Account
import sb.demo.repository.entities.Bank
import sb.demo.repository.entities.BankUser

@Service
class BankService(
    private val bankRepo: BankRepository,
    private val userRepo: UserRepository,
    private val accRepo: AccountRepository,
) {
    // @Autowired
    // private lateinit var bankRepo: BankRepository // Rarely used after Spring5. Auto-injection from constructor.

    @Transactional(readOnly = true)
    fun fetchBanks(): List<Bank> {
        return bankRepo.findAllByFeeGreaterThan(10)
    }

    @Transactional
    fun saveBank(): BankDTO {
        // Must save the transient instance account1/user1 before flushing
        val user1 = userRepo.save(BankUser("Lebron", 38, "LA"))

        val acc1 = accRepo.save(Account(1_000, user1))
        val acc2 = accRepo.save(Account(2_000, user1))

        user1.accounts = mutableListOf(acc1, acc2)
        // userRepo.save(user1) // No need to flush here

        val bank1 = Bank(bankName = "scb", users = mutableListOf(user1))
        val newBank = bankRepo.save(bank1)
        println("Saved bank: $newBank")
        return BankDTO(newBank)
    }
}