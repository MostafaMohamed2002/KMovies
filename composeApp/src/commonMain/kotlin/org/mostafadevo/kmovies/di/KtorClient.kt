package org.mostafadevo.kmovies.di

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.mostafadevo.kmovies.utils.Constants

object KtorClient {
    val client: HttpClient
        get() = HttpClient(OkHttp) {
            //Timeout plugin to set up timeout milliseconds for client
            install(HttpTimeout) {
                socketTimeoutMillis = 60_000
                requestTimeoutMillis = 60_000
            }
            //Logging plugin combined with kermit(KMP Logger library)
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.d { message }
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }
            defaultRequest {
                header("Content-Type", "application/json")
                header("Authorization", "Bearer ${Constants.API_KEY}")
                url(Constants.BASE_URL)
            }
        }
}