val koin_version: String by project
val ipfs_api_version: String by project

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
    //IPFS
    implementation("com.github.ipfs:java-ipfs-http-client:$ipfs_api_version")
    //单元测试
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}