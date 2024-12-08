import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.reload.ComposeHotRun
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization") version "2.1.0"
    id("org.jetbrains.compose-hot-reload")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            // Koin
            implementation(libs.koin.android)
            implementation(libs.ktor.client.okhttp)

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            // Material Kolor for generating color scheme
            implementation(libs.materialKolor)
            // KMPalette
            // Core library
            implementation(libs.kmpalette.core)
            // Optional extensions based on your image source
            implementation(libs.kmpalette.extensions.base64)
            implementation(libs.kmpalette.extensions.bytearray)
            implementation(libs.kmpalette.extensions.libres)
            implementation(libs.kmpalette.extensions.network)
            implementation(libs.kmpalette.extensions.file)
            //Ktor client
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.encoding)
            //Landscapist
            val version = "2.4.4"
            implementation("com.github.skydoves:landscapist-coil3:$version")
            // those below plugin dependencies also support Kotlin Multiplatform.
            implementation("com.github.skydoves:landscapist-placeholder:$version")
            implementation("com.github.skydoves:landscapist-animation:$version")
            implementation("com.github.skydoves:landscapist-palette:$version")
            // Koin
            implementation(libs.koin.core)
            // Kermit for logging
            implementation("co.touchlab:kermit:2.0.5")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }

}

android {
    namespace = "org.mostafadevo.kmovies"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.mostafadevo.kmovies"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "org.mostafadevo.kmovies.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.mostafadevo.kmovies"
            packageVersion = "1.0.0"
        }
    }
}
tasks.register<ComposeHotRun>("runHot") {
    mainClass.set("org.mostafadevo.kmovies.MainKt")
}