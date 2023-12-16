package com.glex.deps.cfg

import java.io.File
import java.util.Properties

object Configuration {

    private val properties = Properties()

    val serverHost: String by lazy { properties.getProperty("serverHost") }
    val serverPort: String by lazy { properties.getProperty("serverPort") }

    fun init() {
        val file = File("cfg", "config.properties")
        properties.load(file.reader())
    }
}
