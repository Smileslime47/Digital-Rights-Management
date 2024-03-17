package moe._47saikyo.configuration

import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
//        json(Json {
//            prettyPrint = true
//            isLenient = true
//        })
//        jackson {
//            enable(SerializationFeature.INDENT_OUTPUT)
//        }
        register(ContentType.Application.Json, JacksonConverter())
    }
    routing {
        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}
