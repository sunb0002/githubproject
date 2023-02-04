package sb.demo.model

import com.fasterxml.jackson.annotation.JsonProperty
import sb.demo.repository.entities.Bank
import sb.demo.repository.entities.BankUser
import java.time.LocalDateTime

// Plain old Java style DTO. Rarely used.
class BankOne {
    var bankId: String // Kotlin class fields are always accessed through accessors, no need to define getter/setter manually
        get() = "bank-$field"
    private val fee: Int

    constructor(bankId: String, fee: Int) {
        this.bankId = bankId
        this.fee = fee
    }
    // implement hashCode, equals, toString (as DTO for jackson/fastjson)
}

// DTO with normal class + primary constructor
// val: exposes both constructor and getter/setter; private+val: expose only constructor
class BankTwo(val bankId: String, private val fee: Int)

// DTO with data class: auto generate toString, component (de-constructor), hashCode, equals, etc.
// Note: data classes can't be inherited
data class BankThree(val bankId: String, val fee: Int)

data class BankFour(val bankName: String, val fee: Int, val users: List<BankUser>) {
    constructor(entity: Bank) : this(entity.bankName, entity.fee, entity.users)
}

typealias BankDTO = BankFour

data class BankUserDTO(val userName: String, val age: Int, @JsonProperty("homeTown") val city: String)
data class GeneralError(val message: String) {
    // Will not appear in "toString", but still appear in serialization (JSON)
    val date: LocalDateTime = LocalDateTime.now()
}