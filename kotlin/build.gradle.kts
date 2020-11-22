import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("kapt") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
    application
    java
}
group = "me.marko"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    sourceSets {
        getByName("main").java.srcDir("src/main/java")
    }
}

repositories {
    jcenter()
    maven { url = uri("https://dl.bintray.com/arrow-kt/arrow-kt/") }
    maven { url = uri("https://oss.jfrog.org/artifactory/oss-snapshot-local/") }
}

val arrowVersion = "0.11.0"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation(kotlin("test-junit"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0-M1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.3.2")
    implementation("io.arrow-kt:arrow-core:$arrowVersion")
    implementation("io.arrow-kt:arrow-syntax:$arrowVersion")
    kapt("io.arrow-kt:arrow-meta:$arrowVersion")
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
application {
    mainClassName = "MainKt"
}