val kotlin_version:String by project
val koin_version: String by project
val logback_version: String by project
val web3j_version: String by project

plugins {
    kotlin("jvm") version "1.9.22"
}

group = "moe._47saikyo"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project("::drm-core"))
    //依赖注入
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    //Log框架
    implementation("ch.qos.logback:logback-classic:$logback_version")
    //web3j
    implementation("org.web3j:core:$web3j_version")
    //jackson kotlin反序列化支持
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.+")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}