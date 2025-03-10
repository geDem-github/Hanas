plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ktlint)
    kotlin(libs.plugins.serialization.get().pluginId).version(libs.versions.serialization)
}

android {
    namespace = "com.example.hanas.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.example.hanas.android"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)

    // Navigation
    implementation(libs.compose.navigation)
    // Serialization
    implementation(libs.kotlinx.serialization.json)
    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.viewmodel)
    implementation(libs.koin.viewmodel.navigation)
    // Ktor
    implementation(libs.ktor.client.okhttp)
    // ViewModel
    implementation(libs.compose.viewmodel)
}
