package com.example.mwoyeottheora.infrastructure.word

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class FoundWord(
    val name: String,
    @Column(length = 16)
    val userId: UUID
) {
    @Id
    @Column(length = 16)
    val id: UUID = UUID.randomUUID()
}
