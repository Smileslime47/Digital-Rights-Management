package moe._47saikyo.drm.backend.configuration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun Application.configureHTTP() {
    //配置CORS策略
    install(CORS) {
//        //CORS默认允许以下method
//        allowMethod(HttpMethod.Get)
//        allowMethod(HttpMethod.Post)
//        allowMethod(HttpMethod.Head)
        allowMethod(HttpMethod.Options)
//        //CORS默认允许以下headers
//        allowHeader(HttpHeaders.Accept)
//        allowHeader(HttpHeaders.AcceptLanguage)
//        allowHeader(HttpHeaders.ContentLanguage)
        //以下不默认允许
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.AcceptEncoding)
        allowHeader(HttpHeaders.Connection)
        allowHeader(HttpHeaders.ContentLength)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Host)
        allowHeader(HttpHeaders.Origin)
        allowHeader(HttpHeaders.Referrer)
        allowHeader(HttpHeaders.UserAgent)
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }
    //配置Swagger路径
    routing {
        swaggerUI(path="openapi", swaggerFile = "openapi/Document.yaml")
    }
}
