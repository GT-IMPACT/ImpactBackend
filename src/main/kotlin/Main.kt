import com.glex.deps.cfg.Configuration
import com.glex.deps.params.configureMonitoring
import com.glex.deps.params.configureSerialization
import com.glex.deps.routes.configureRoutes
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
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
            
            HOST: ${environment.config.host}
            PORT: ${environment.config.port}
            
            ================================
            
        """.trimIndent()
    )
    configureMonitoring()
    configureSerialization()
    configureRoutes()
    install(StatusPages)
}
