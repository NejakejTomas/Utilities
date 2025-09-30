plugins {
//    alias(libs.plugins.androidApplication) apply false
//    alias(libs.plugins.composeMultiplatform) apply false
//    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.vanniktech.mavenPublish) apply false
}

tasks.register("clean") {
    rootProject.layout.buildDirectory.asFile.get().deleteRecursively()
}