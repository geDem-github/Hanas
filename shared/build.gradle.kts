import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ktlint)
    kotlin(libs.plugins.serialization.get().pluginId).version(libs.versions.serialization)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Serialization
            implementation(libs.kotlinx.serialization.json)
            // Koin
            implementation(libs.koin.core)
            // Coroutines
            implementation(libs.kotlinx.coroutines)
            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization)
            // Napier
            implementation("io.github.aakira:napier:2.7.1")
        }
        iosMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.example.hanas"
    compileSdk = 35
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        minSdk = 29
        val localProperties = Properties()
        localProperties.load(FileInputStream(project.rootProject.file("local.properties")))
        buildConfigField(
            type = "String",
            name = "OPENAI_API_TOKEN",
            value = "\"${localProperties.getProperty("OPENAI_API_TOKEN")}\"",
        )
        buildConfigField(
            type = "String",
            name = "CHAT_GPT_API_BASE_URL",
            value = "\"${localProperties.getProperty("CHAT_GPT_API_BASE_URL")}\"",
        )
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
