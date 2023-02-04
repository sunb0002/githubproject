package sb.demo.repository

import org.springframework.data.jpa.repository.JpaRepository
import sb.demo.repository.entities.Account
import sb.demo.repository.entities.Bank
import sb.demo.repository.entities.BankUser

interface BankRepository : JpaRepository<Bank, Long> {
    fun findByFee(fee: Int): Bank
    fun findAllByFeeGreaterThan(fee: Int): List<Bank>
}

interface UserRepository : JpaRepository<BankUser, Long>
interface AccountRepository : JpaRepository<Account, Long>
