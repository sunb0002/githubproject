package sb.demo.repository.entities

import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Version

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @Version
    var version: Long = 0L

    @LastModifiedDate
    @Column(nullable = false)
    var updateDate: LocalDateTime = LocalDateTime.now()
        protected set
}
