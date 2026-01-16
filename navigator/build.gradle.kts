import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

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

    listOf(iosArm64(), iosSimulatorArm64()).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Navigator"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {

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

            implementation(libs.kotlin.stdlib)
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
    namespace = "com.walcker.flickly.navigator"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
}