import com.glex.deps.cfg.Configuration
import com.glex.deps.params.configureMonitoring
import com.glex.deps.params.configureSerialization
import com.glex.deps.routes.configureRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.statuspages.*

fun main(args: Array<String>) {

    Configuration.init()

    embeddedServer(
        factory = Netty,
        port = Configuration.serverPort.toInt(),
        host = Configuration.serverHost,
        module = Application::init,
    ).start(wait = true)
}

fun Application.init() {
    println(
        """
            
            ======== SERVER STARTED ========
            
            HOST: ${Configuration.serverHost}
            PORT: ${Configuration.serverPort}
            
            ================================
            
        """.trimIndent()
    )
    configureMonitoring()
    configureSerialization()
    configureRoutes()
    install(StatusPages)
    install(CORS) {
        anyHost()
        HttpMethod.DefaultMethods.forEach(::allowMethod)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
    }
}
