import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinKsp)
    alias(libs.plugins.detekt)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
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
            baseName = "AppMan"
            isStatic = true
            export(projects.core)
        }
    }
    sourceSets {
        androidMain.dependencies {
            implementation(projects.products.movies)

            implementation(libs.bundles.voyagerEcosystem)
            implementation(libs.bundles.koinEcosystem)

            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.androidx.foundation)

            implementation(libs.firebase.analytics)
            implementation(libs.firebase.crashlytics.ndk)
            implementation(libs.firebase.gitlive.app)
            implementation(libs.firebase.gitlive.storage)
        }
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.navigator)
            implementation(projects.products.movies)
            implementation(projects.products.audio)
            implementation(projects.cedarDS)
        }
        iosMain.dependencies {
            api(projects.core)
            implementation(projects.products.movies)
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.walcker.flickly.app"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    val localProps = Properties().apply {
        val f = rootProject.file("local.properties")
        if (f.exists()) load(f.inputStream())
    }

    defaultConfig {
        applicationId = "com.walcker.flickly.app"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 2502
        versionName = "26.0.1-alpha"

        buildConfigField(
            "String",
            "TMDB_ACCESS_TOKEN",
            "\"${localProps.getProperty("TMDB_ACCESS_TOKEN", "")}\""
        )
    }

    signingConfigs {
        create("release") {
            val storeFilePath = localProps.getProperty("release.storeFile") ?: "../keystore.jks"
            val storePassword = localProps.getProperty("release.storePassword") ?: ""
            val keyAlias = localProps.getProperty("release.keyAlias") ?: ""
            val keyPassword = localProps.getProperty("release.keyPassword") ?: ""

            if (storeFilePath.isNotBlank()) {
                storeFile = file(storeFilePath)
            }
            this.storePassword = storePassword
            this.keyAlias = keyAlias
            this.keyPassword = keyPassword
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/INDEX.LIST"
            excludes += "/META-INF/DEPENDENCIES"
            excludes += "DebugProbesKt.bin"
        }
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            isShrinkResources = true
            applicationIdSuffix = ".release"
            versionNameSuffix = "-release"
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
        isCoreLibraryDesugaringEnabled = true
    }
    buildFeatures { buildConfig = true; compose = true }
    composeOptions { kotlinCompilerExtensionVersion = libs.versions.kotlin.get() }
}

dependencies {
    debugImplementation(compose.uiTooling)
    coreLibraryDesugaring(libs.desugar.jdk.libs)
}