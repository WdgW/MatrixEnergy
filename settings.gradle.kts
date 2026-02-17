pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    }
    plugins {
        id("org.jetbrains.kotlin.jvm") version "2.2.20"
    }
}

rootProject.name = "MatrixEnergy"

include(":library", ":genesis")
