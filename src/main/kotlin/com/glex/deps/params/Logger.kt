package com.glex.deps.params

import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import org.slf4j.event.*

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO
    }
}