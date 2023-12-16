package com.glex.deps.services.update_mod

import com.glex.deps.services.update_mod.routes.addPostUploadMod
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.addUpdateModVersion() {
    routing {
        addPostUploadMod()
    }
}
