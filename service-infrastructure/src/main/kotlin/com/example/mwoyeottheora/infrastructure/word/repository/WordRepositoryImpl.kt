package com.example.mwoyeottheora.infrastructure.word.repository

import com.example.mwoyeottheora.infrastructure.word.FoundWord
import com.example.mwoyeottheora.infrastructure.word.service.WordApiDto
import com.example.mwoyeottheora.infrastructure.word.service.WordApiListResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.linecorp.kotlinjdsl.query.HibernateMutinyReactiveQueryFactory
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.selectQuery
import io.smallrye.mutiny.coroutines.awaitSuspending
import java.net.URI
import java.util.UUID
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.MediaType
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.UriBuilder

@Repository
class WordRepositoryImpl(
    private val reactiveQueryFactory: HibernateMutinyReactiveQueryFactory,
    private val webClient: WebClient,
    private val objectMapper: ObjectMapper
) : WordRepository {
    override suspend fun findAllByNameLike(name: String): List<String> {
        return webClient.get()
            .uri { uri -> uri.buildQueryWordEndpoint(name) }
            .accept(MediaType.APPLICATION_JSON)
            .accept(MediaType.TEXT_PLAIN)
            .retrieve().handleQueryWordResponse()
    }

    private fun UriBuilder.buildQueryWordEndpoint(name: String): URI {
        return this
            .scheme("http")
            .host("opendict.korean.go.kr")
            .path("/api/search")
            .queryParam("q", name)
            .queryParam("advanced", "y")
            .queryParam("type1", "word")
            .queryParam("pos", 1)
            .queryParam("sort", "popular")
            .queryParam("method", "include")
            .queryParam("req_type", "json")
            .queryParam("key", "B35E42B63A2326E5E77F81EA9B65260E")
            .build()
    }

    private suspend fun ResponseSpec.handleQueryWordResponse(): List<String> {
        val apiResponse = this.awaitBody<String>()
        return objectMapper.readValue<WordApiDto>(apiResponse).channel.item.map { it.word }
    }

    override suspend fun save(word: FoundWord): FoundWord {
        reactiveQueryFactory.withFactory { session, _ ->
            session.merge(word).await()
        }
        return word
    }

    override suspend fun findAllByUserId(userId: UUID): List<FoundWord> {
        return reactiveQueryFactory.withFactory { _, reactiveQueryFactory ->
            reactiveQueryFactory.selectQuery<FoundWord> {
                select(entity(FoundWord::class))
                from(entity(FoundWord::class))
                where(col(FoundWord::userId).equal(userId))
            }.resultList()
        }
    }

    override suspend fun findRandom(): String? {
        return reactiveQueryFactory.withFactory { session, _ ->
            session.createNativeQuery<String>("SELECT name FROM found_word ORDER BY RAND() LIMIT 1;")
                .singleResultOrNull
                .awaitSuspending()
        }
    }
}
