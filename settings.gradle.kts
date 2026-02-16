rootProject.name = "MatrixEnergy"

include(":library", ":genesis")

pluginManagement {
    plugins {
        java
        kotlin("jvm") version "2.2.20" apply false
    }
}
