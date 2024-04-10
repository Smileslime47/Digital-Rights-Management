val kotlin_version: String by project
val koin_version: String by project
val ktor_version: String by project
val jbcrypt_version: String by project
val logback_version: String by project
val web3j_version: String by project
val exposed_version: String by project
val h2_version: String by project
val mariadb_version: String by project

plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.7"
}

group = "moe._47saikyo"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(project("::drm-core"))
    implementation(project("::drm-blockchain"))
    //依赖注入
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    //Ktor框架
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-server-auth-jwt")
    implementation("io.ktor:ktor-server-auth-jwt-jvm")
    implementation("io.ktor:ktor-server-cors-jvm")
    implementation("io.ktor:ktor-server-swagger-jvm")
    implementation("io.ktor:ktor-server-config-yaml")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-jackson-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    //加密库
    implementation("org.mindrot:jbcrypt:$jbcrypt_version")
    //Log框架
    implementation("ch.qos.logback:logback-classic:$logback_version")
    //web3j
    implementation("org.web3j:core:$web3j_version")
    //IPFS
    implementation("com.github.ipfs:java-ipfs-http-client:1.4.4")
    //Dao层框架——Exposed
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("com.h2database:h2:$h2_version")
    // mariaDB driver
    implementation("org.mariadb.jdbc:mariadb-java-client:$mariadb_version")
    //单元测试框架
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}