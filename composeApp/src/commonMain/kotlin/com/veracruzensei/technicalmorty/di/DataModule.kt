package com.veracruzensei.technicalmorty.di

import com.veracruzensei.technicalmorty.data.RepositoryImplementation
import com.veracruzensei.technicalmorty.data.remote.ApiService
import com.veracruzensei.technicalmorty.data.remote.paging.CharactersPagingSource
import com.veracruzensei.technicalmorty.domain.Repository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val DataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "rickandmortyapi.com"
                }
            }
        }
    }
    factoryOf(::ApiService)
    factory<Repository> { RepositoryImplementation(get(), get()) }
    factoryOf(::CharactersPagingSource)
}
