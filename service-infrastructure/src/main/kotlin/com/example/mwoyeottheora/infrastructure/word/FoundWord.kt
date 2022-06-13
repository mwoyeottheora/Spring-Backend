package com.example.mwoyeottheora.infrastructure.word

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class FoundWord(
    val name: String,
    val userId: UUID
) {
    @Id
    val id: UUID = UUID.randomUUID()
}
