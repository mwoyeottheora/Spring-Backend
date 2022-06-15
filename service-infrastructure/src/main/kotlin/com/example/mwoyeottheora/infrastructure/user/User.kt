package com.example.mwoyeottheora.infrastructure.user

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import org.hibernate.annotations.NaturalId

@Entity
class User(
    @NaturalId
    val accountId: String,
    val password: String
) {
    @Id
    @Column(length = 16)
    val id: UUID = UUID.randomUUID()
}
