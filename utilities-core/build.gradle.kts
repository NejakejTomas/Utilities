import org.gradle.internal.extensions.stdlib.capitalized
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.parcelize)
    id("maven-publish")
}

group = libs.versions.library.group.get()
version = libs.versions.library.version.get()

kotlin {
    jvm()
    android {
        namespace = "${libs.versions.library.group.get()}.${project.name.replace('-', '.')}"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.core)
        }
    }
}

mavenPublishing {
    coordinates(group.toString(), project.name, version.toString())

    pom {
        name = project.name.split('-').joinToString(" ") { it.capitalized() }
        inceptionYear = "2025"
    }
}
