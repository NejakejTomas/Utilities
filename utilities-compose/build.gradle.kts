import org.gradle.internal.extensions.stdlib.capitalized
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.composeCompiler)
    id("maven-publish")
}

group = libs.versions.library.group.get()
version = libs.versions.library.version.get()

kotlin {
    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    android {
        namespace = "${libs.versions.library.group.get()}.${project.name.replace('-', '.')}"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    iosArm64()
    iosSimulatorArm64()

    compilerOptions {
        freeCompilerArgs.add("-Xreturn-value-checker=full")
        optIn.add("kotlin.uuid.ExperimentalUuidApi")
    }

    sourceSets {
        androidMain.dependencies {
            api(libs.accompanist.permissions)
        }

        commonMain.dependencies {
            implementation(project(":utilities-core"))
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.lifecycle.navigation3)
            implementation(libs.navigation3.ui)

            api(libs.compose.material3)
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