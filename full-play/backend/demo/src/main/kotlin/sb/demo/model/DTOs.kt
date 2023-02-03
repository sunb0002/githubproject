package sb.demo.model

import com.fasterxml.jackson.annotation.JsonProperty

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


typealias BankDTO = BankThree

data class BankUserDTO(val userName: String, val age: Int, @JsonProperty("homeTown") val city: String)
