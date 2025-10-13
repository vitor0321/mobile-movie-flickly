plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization") version "2.2.0"
}

kotlin {
    androidLibrary {
        namespace = "com.walcker.movies.products.batSignal"
        compileSdk = 36
        minSdk = 24
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "BatSignal"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.okhttp)
        }
        androidUnitTest.dependencies {
            implementation(libs.paparazzi)
            implementation(libs.parameter.injector)
        }
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.navigator)

            implementation(libs.bundles.ktorEcosystem)
            implementation(libs.bundles.voyagerEcosystem)
            implementation(libs.bundles.koinEcosystem)

            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.navigation)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)
            implementation(libs.collections.immutable)
            implementation(libs.composeIcons.fontAwesome)
            implementation(libs.compose.shimmer)
            implementation(libs.mediaplayer.kmp)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.serialization)
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.lyricist)
        }
        commonTest.dependencies {
            implementation(libs.bundles.commonTestEcosystem)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}