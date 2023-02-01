package sb.demo.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BankServiceTest {

    @Test
    @DisplayName("hello1 display name")
    fun service1() {
        val numbers = listOf(1, 2, 3)
        Assertions.assertThat(numbers).allMatch { it > 0 }
        Assertions.assertThat(numbers).anyMatch { it > 0 }
    }

    @Test
    fun fetchBanks() {
    }
}