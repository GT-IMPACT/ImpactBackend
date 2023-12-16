package com.glex.deps.routes

import com.glex.deps.services.update_mod.addUpdateModVersion
import io.ktor.server.application.*

fun Application.configureRoutes() {
    addUpdateModVersion()
}
