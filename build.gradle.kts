plugins {
    kotlin("jvm") version "1.9.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
    id("io.ktor.plugin") version "2.3.5"
    application
}

group = "space.impact"
version = "1.0"

application {
    mainClass.set("MainKt")
}

repositories {
    mavenCentral()
}

tasks.register("deploy") {
    doLast {
        val file = File(buildDir, "libs")
        val deploy = File(rootDir, "deploy")
        val jar = file.listFiles()?.firstOrNull()!!
        jar.copyTo(File(deploy, jar.name), overwrite = true)
        val shStarter = File(deploy, "start.sh")
        shStarter.createNewFile()
        shStarter.writeText("java -server -jar ${jar.name}")
    }
    dependsOn(tasks.shadowJar)
}

dependencies {
    implementation("io.ktor:ktor-server-call-logging-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-swagger-jvm")
    implementation("io.ktor:ktor-server-openapi")
    implementation("io.ktor:ktor-server-resources")
//    implementation("io.ktor:ktor-server-host-common-jvm")
//    implementation("io.ktor:ktor-server-auth-jvm")
//    implementation("io.ktor:ktor-server-auth-jwt-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-status-pages")
    implementation("io.ktor:ktor-server-cors")
    implementation("io.ktor:ktor-server-html-builder")

//    implementation("io.ktor:ktor-client-core:2.3.5")
//    implementation("io.ktor:ktor-client-cio:2.3.5")
//    implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")

//    implementation("org.ktorm:ktorm-core:3.6.0")
//    implementation("io.insert-koin:koin-core:3.5.0")
//    implementation("io.insert-koin:koin-ktor:3.5.0")
//    implementation("org.postgresql:postgresql:42.2.27")
//    implementation("org.mindrot:jbcrypt:0.4")

}
