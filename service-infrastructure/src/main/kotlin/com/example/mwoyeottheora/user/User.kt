package com.example.mwoyeottheora.user

import org.hibernate.annotations.NaturalId
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(
    @NaturalId
    val accountId: String
) {
    @Id
    val id: UUID = UUID.randomUUID()
}
