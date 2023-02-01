package sb.demo.repository.entities

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
    @OneToMany
    var users: MutableList<BankUser> = mutableListOf()
) : BaseEntity()

@Entity
data class BankUser(
    var userName: String = "",
    @Column(nullable = false) var age: Int = 0,
    @Column(length = 10) var city: String = "",
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var userId: Long? = null
) : BaseEntity()