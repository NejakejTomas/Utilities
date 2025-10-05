import org.gradle.internal.extensions.stdlib.capitalized
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.parcelize)
    id("maven-publish")
}

group = libs.versions.library.group.get()
version = libs.versions.library.version.get()

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.core)
            }
        }
    }
}

android {
    namespace = "${libs.versions.library.group.get()}.${project.name.replace('-', '.')}"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

mavenPublishing {
    coordinates(group.toString(), project.name, version.toString())

    pom {
        name = project.name.split('-').joinToString(" ") { it.capitalized() }
        inceptionYear = "2025"
    }
}
