import org.gradle.internal.extensions.stdlib.capitalized
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
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
        freeCompilerArgs.add("-Xreturn-value-checker=full")
        optIn.add("kotlin.uuid.ExperimentalUuidApi")
    }

    sourceSets {
        androidMain.dependencies {
            api(libs.accompanist.permissions)
        }

        commonMain.dependencies {
            implementation(project(":utilities-core"))
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.lifecycle.navigation3)
            implementation(libs.navigation3.ui)

            api(compose.material3)
            api(libs.kotlinx.coroutines.core)
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
