package com.example.mwoyeottheora.infrastructure.user

import org.hibernate.annotations.NaturalId
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(
    @NaturalId
    val accountId: String,
    val password: String
) {
    @Id
    val id: UUID = UUID.randomUUID()
}
