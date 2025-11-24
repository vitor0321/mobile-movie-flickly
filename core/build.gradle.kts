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
}

kotlin {
    jvmToolchain(21)
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions { jvmTarget.set(JvmTarget.JVM_21) }
    }

    listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Core"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.firebase.storage)
            implementation(libs.kotlinx.coroutines.play.services)
        }
        androidUnitTest.dependencies {
            implementation(libs.bundles.androidTestEcosystem)
        }
        commonMain.dependencies {
            implementation(libs.bundles.koinEcosystem)
            implementation(libs.bundles.voyagerEcosystem)

            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(libs.lyricist)

            implementation(libs.kotlin.stdlib)
            implementation(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
        }
        iosMain.dependencies {

        }
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