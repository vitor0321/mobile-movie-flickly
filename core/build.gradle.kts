import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinKsp)
    alias(libs.plugins.detekt)
    kotlin("plugin.serialization") version libs.versions.kotlin.get()
    kotlin("native.cocoapods")
}

kotlin {
    jvmToolchain(21)
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions { jvmTarget.set(JvmTarget.JVM_21) }
    }

    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        name = "Core"
        version = "1.0"
        summary = "Core KMP module for Flickly"
        homepage = "https://github.com/walcker/flickly"
        ios.deploymentTarget = "14.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "Core"
            isStatic = true
        }
        pod("FirebaseCore")
        pod("FirebaseStorage")
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.play.services)
            implementation(libs.firebase.storage)
            implementation(libs.androidyoutubeplayer.core)
        }
        androidUnitTest.dependencies {
            implementation(libs.bundles.androidTestEcosystem)
        }
        commonMain.dependencies {
            implementation(libs.bundles.koinEcosystem)
            implementation(libs.bundles.voyagerEcosystem)

            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.preview)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(libs.lyricist)
            implementation(libs.composeIcons.fontAwesome)

            implementation(libs.kotlin.stdlib)
            implementation(libs.kotlinx.datetime)
            implementation(libs.multiplatform.settings.no.arg)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
        }
        iosMain.dependencies {
        }
    }
}

// Configure Compose Resources for iOS
compose {
    resources {
        publicResClass = false
        packageOfResClass = "flickly.core.generated.resources"
        generateResClass = always
    }
}

android {
    namespace = "com.walcker.flickly.core"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) localProperties.load(localPropertiesFile.inputStream())
        buildConfigField("String", "TMDB_ACCESS_TOKEN", "\"${localProperties.getProperty("TMDB_ACCESS_TOKEN", "")}\"")
    }
    packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}"; excludes += "/META-INF/INDEX.LIST"; excludes += "/META-INF/DEPENDENCIES"; excludes += "DebugProbesKt.bin" } }
    buildFeatures { buildConfig = true }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_21; targetCompatibility = JavaVersion.VERSION_21; isCoreLibraryDesugaringEnabled = true }
}

dependencies {
    debugImplementation(compose.uiTooling)
    coreLibraryDesugaring(libs.desugar.jdk.libs)
}

// Compose resources for iOS are handled by the "Copy Compose Resources" Xcode build phase
afterEvaluate {
    tasks.matching { it.name.contains("syncPodComposeResources") }.configureEach {
        enabled = false
    }
}
