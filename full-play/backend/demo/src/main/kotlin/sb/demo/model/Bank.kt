package sb.demo.model

// Plain old Java style DTO
class BankOne {
    private val bankId: String
        get() = "bank-$field"
    private val fee: Int

    constructor(bankId: String, fee: Int) {
        this.bankId = bankId
        this.fee = fee
    }
    // implement hashCode, equals, toString (as DTO for jackson/fastjson)
}

// DTO with Kotlin primary constructor
// val here exposes both constructor and getter/setter, private+val doesn't expose getter/setter
class BankTwo(val bankId: String, private val fee: Int) {
    // implement hashCode, equals, toString
}

// DTO with data class, auto generate hashCode, equals, toString, etc.
// data classes can't be inherited
data class BankThree(val bankId: String, val fee: Int)
