package sb.demo.repository.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

// (1) Don't use SQL reserved words like "user" or "key" as table name
// (2) JPA requires no-arg default constructor, so must assign defaultValue to each field, or use gradle plugin

@Entity
data class Bank(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var bankId: Long? = null,
    @Column(nullable = false, length = 5)
    var bankName: String = "",
    var fee: Int = 20,
    @OneToMany // will create middle-man table
    var users: MutableList<BankUser> = mutableListOf()
) : BaseEntity()

@Entity
data class BankUser(
    @Column(nullable = false, unique = true) var userName: String = "",
    @Column(nullable = false) var age: Int = 0,
    @Column(length = 10) var city: String = "",
    @JsonManagedReference @OneToMany(mappedBy = "bankUser") var accounts: MutableList<Account> = mutableListOf(), // will create foreign key
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var userId: Long? = null
) : BaseEntity()

@Entity
data class Account(
    @Column(nullable = false) val balance: Int = 0,
    @JsonBackReference @JoinColumn @ManyToOne val bankUser: BankUser? = null,
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var accId: Long? = null
) : BaseEntity() {
    // Caveat: Kotlin/Lombok generated toString may cause infinite loop, better override in such case.
    // Same for @JsonManagedReference + @JsonBackReference (or just @JsonIgnore)
    // https://stackoverflow.com/questions/47127427/stackoverflowerror-with-jpa-bidirectional-references-in-kotlin
    override fun toString(): String = "Account(accId=$accId,balance=$balance)"
}