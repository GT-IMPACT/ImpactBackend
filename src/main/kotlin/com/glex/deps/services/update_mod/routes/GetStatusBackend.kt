package com.glex.deps.services.update_mod.routes

import com.glex.deps.core.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.addGetStatusBackend() {
    get("status") {
        call.respond(HttpStatusCode.OK, Response(data = "Status: OK"))
    }
}
