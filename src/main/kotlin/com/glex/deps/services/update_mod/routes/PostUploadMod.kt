package com.glex.deps.services.update_mod.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private const val CDN_UPLOAD_FOLDER = "cdn/uploads"
private const val MODS_CLIENT_FOLDER = "client/.minecraft/mods"
private const val MODS_SERVER_FOLDER_ = "server/mods"
private const val IMPACT1_FOLDER = "modpacks/impact-1-experimental"

fun Routing.addPostUploadMod() {
    post("upload/impact1") {
        val folderMods = File(IMPACT1_FOLDER, MODS_CLIENT_FOLDER)
        uploadMod(folderMods)
    }
}

suspend fun PipelineContext<*, ApplicationCall>.uploadMod(folderMods: File) = withContext(Dispatchers.IO) {

    val name = call.parameters["name"] ?: return@withContext call.respond(HttpStatusCode.BadRequest)
    val modId = call.parameters["modid"] ?: return@withContext call.respond(HttpStatusCode.BadRequest)
    val version = call.parameters["version"] ?: return@withContext call.respond(HttpStatusCode.BadRequest)

    val found = folderMods.listFiles()?.find { it.name.contains(modId) }

    val date = LocalDateTime
        .now(ZoneId.of("Europe/Moscow"))
        .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))

    if (found?.name == name) {
        println("=== $date\nThis File already created: ${found.name}\n===")
        return@withContext call.respond(HttpStatusCode.OK)
    } else {

        found?.also {
            if (!it.deleteRecursively()) println("Error Delete ${it.name}")
            else println("=== $date\nUPDATE: ${it.name} ----> $name\n===")
        } ?: run {
            println("=== $date\nNot Found: $modId")
            println("\nUPDATE: Added ----> $name\n===")
        }

        val modFile = call.receiveStream().use {
            val file = File(CDN_UPLOAD_FOLDER)
            file.mkdirs()

            val uploadedFile = File(file, name)
            uploadedFile.createNewFile()
            uploadedFile.writeBytes(it.readBytes())

            uploadedFile
        }

        modFile.copyTo(File(folderMods, modFile.name), overwrite = true)
        modFile.delete()

        return@withContext call.respond(HttpStatusCode.OK)
    }
}
