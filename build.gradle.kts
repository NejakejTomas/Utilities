plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.vanniktech.mavenPublish) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
}

tasks.register("clean") {
    rootProject.layout.buildDirectory.asFile.get().deleteRecursively()
}