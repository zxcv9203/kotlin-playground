plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // 코루틴 코어
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // 코루틴 + JDK와의 통합 (delay 등)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.3")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

