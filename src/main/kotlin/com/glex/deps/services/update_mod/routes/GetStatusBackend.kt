package com.glex.deps.services.update_mod.routes

import com.glex.deps.core.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.body
import kotlinx.html.h1

fun Routing.addGetStatusBackend() {
    get("status") {
        call.respond(HttpStatusCode.OK, Response(data = "Status: OK"))
    }
    get("/") {
        call.respondHtml(HttpStatusCode.OK) {
            body {
                h1 {
                    + "IMPACT UPDATER"
                }
            }
        }
    }
}
