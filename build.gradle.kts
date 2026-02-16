// build.gradle.kts

// 从 gradle.properties 或命令行参数读取属性
val kotlinVersion = "2.2.20"
val kryoVersion = "5.6.0"
val mindustryVersion = "v155"
val jabelVersion = "93fde537c7"
val modName: String = "MatrixEnergy"
val modNameL: String = "MatrixEnergyLib"
val modNameG: String = "MatrixEnergyGenesis"

buildscript {
    repositories {
        maven {
            url = uri("https://maven.aliyun.com/repository/gradle-plugin")
        }
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.20")
//        classpath("com.github.Anuken.Mindustry:core:v155")
    }

}

plugins {
    java
    kotlin("jvm") version "2.2.20" apply false
}


allprojects {
    group = "matrix_energy"
    version = "0.1.0-dev"

    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.aliyun.com/repository/public")
        }
        maven {
            url = uri("https://maven.aliyun.com/repository/google")
        }
        maven {
            url = uri("https://raw.githubusercontent.com/Zelaux/MindustryRepo/master/repository")
        }
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        compileOnly("com.github.Anuken.Arc:arc-core:$mindustryVersion")
        compileOnly("com.github.Anuken.Mindustry:core:$mindustryVersion")
        annotationProcessor("com.github.Anuken.Jabel:jabel:$jabelVersion")
    }

    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.addAll(listOf("--release", "8"))
        options.encoding = "UTF-8"
    }
}

// 配置依赖版本一致性
configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "com.github.Anuken.Arc") {
            useVersion(rootProject.extra["mindustryVersion"] as String)
        }
    }
}

// 根项目配置
project(":") {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    // 正确配置 sourceSets
    configure<SourceSetContainer> {
        named("main") {
            java.srcDirs("src", "library/src", "genesis/src")
            resources.srcDirs("assets")
        }

    }

    dependencies {
        implementation(project(":library"))
        implementation(project(":genesis"))
    }

    tasks.named<Jar>("jar") {
        archiveFileName.set("${modName}Desktop.jar")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(configurations.runtimeClasspath.get().map {
            if (it.isDirectory) it else zipTree(it)
        })

        from(projectDir) {
            include("mod.hjson")
        }

        from("assets") {
            include("**")
        }

        dependsOn(configurations.runtimeClasspath)
    }

    tasks.register("jarAndroid") {
        dependsOn("jar")

        doLast {
            val sdkRoot = System.getenv("ANDROID_HOME") ?: System.getenv("ANDROID_SDK_ROOT")

            if (sdkRoot.isNullOrBlank() || !File(sdkRoot).exists()) {
                throw GradleException("No valid Android SDK found. Ensure that ANDROID_HOME is set to your Android SDK directory.")
            }

            val platformRoot = File("$sdkRoot/platforms/").listFiles()?.sortedDescending()?.find { f ->
                File(f, "android.jar").exists()
            }

            if (platformRoot == null) {
                throw GradleException("No android.jar found. Ensure that you have an Android platform installed.")
            }

            val dependencies = (configurations.compileClasspath.get().toList() +
                    configurations.runtimeClasspath.get().toList() +
                    listOf(File(platformRoot, "android.jar"))).joinToString(" ") {
                "--classpath ${it.path}"
            }

            val isWindows = System.getProperty("os.name").lowercase().contains("windows")
            val d8 = if (isWindows) "d8.bat" else "d8"

            val libsDir = layout.buildDirectory.dir("libs").get().asFile
            val command = mutableListOf<String>()
            command.add(d8)
            command.addAll(dependencies.split(" ").filter { it.isNotBlank() })
            command.addAll(listOf("--min-api", "14", "--output", "${modName}Android.jar", "${modName}Desktop.jar"))

            ProcessBuilder(command)
                .directory(libsDir)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start()
                .waitFor()
        }
    }

    tasks.register<Jar>("deploy") {
        dependsOn("jarAndroid", "jar")

        val libsDir = layout.buildDirectory.dir("libs").get().asFile
        val desktopJarPath = File(libsDir, "${modName}Desktop.jar")
        val androidJarPath = File(libsDir, "${modName}Android.jar")

        archiveFileName.set("$modName-${rootProject.version}.jar")

        from(zipTree(desktopJarPath), zipTree(androidJarPath))

        doLast {
            delete(desktopJarPath, androidJarPath)
        }
    }
}

// library 子项目配置
project(":library") {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    configure<SourceSetContainer> {
        named("main") {
            java.srcDirs("library/src")
            resources.srcDirs("assets/library")
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion") { isTransitive = true }
        implementation("com.esotericsoftware:kryo:$kryoVersion")
    }

    tasks.named<Jar>("jar") {
        archiveFileName.set("${modNameL}Desktop.jar")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(configurations.runtimeClasspath.get().map {
            if (it.isDirectory) it else zipTree(it)
        })

        from(projectDir) {
            include("library/src/mod.hjson")
        }

        from("../assets/library/") {
            include("**")
        }

        dependsOn(configurations.runtimeClasspath)
    }

    tasks.register("jarAndroid") {
        dependsOn("jar")

        doLast {
            val sdkRoot = System.getenv("ANDROID_HOME") ?: System.getenv("ANDROID_SDK_ROOT")

            if (sdkRoot.isNullOrBlank() || !File(sdkRoot).exists()) {
                throw GradleException("No valid Android SDK found. Ensure that ANDROID_HOME is set to your Android SDK directory.")
            }

            val platformRoot = File("$sdkRoot/platforms/").listFiles()?.sortedDescending()?.find { f ->
                File(f, "android.jar").exists()
            }

            if (platformRoot == null) {
                throw GradleException("No android.jar found. Ensure that you have an Android platform installed.")
            }

            val dependencies = (configurations.compileClasspath.get().toList() +
                    configurations.runtimeClasspath.get().toList() +
                    listOf(File(platformRoot, "android.jar"))).joinToString(" ") {
                "--classpath ${it.path}"
            }

            val isWindows = System.getProperty("os.name").lowercase().contains("windows")
            val d8 = if (isWindows) "d8.bat" else "d8"

            val libsDir = layout.buildDirectory.dir("libs").get().asFile
            val command = mutableListOf<String>()
            command.add(d8)
            command.addAll(dependencies.split(" ").filter { it.isNotBlank() })
            command.addAll(
                listOf(
                    "--min-api",
                    "14",
                    "--output",
                    "${modNameL}Android.jar",
                    "${modNameL}Desktop.jar"
                )
            )

            ProcessBuilder(command)
                .directory(libsDir)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start()
                .waitFor()
        }
    }

    tasks.register<Jar>("deploy") {
        dependsOn("jar", "jarAndroid")

        val libsDir = layout.buildDirectory.dir("libs").get().asFile
        val desktopJarPath = File(libsDir, "${modNameL}Desktop.jar")
        val androidJarPath = File(libsDir, "${modNameL}Android.jar")

        archiveFileName.set("$modNameL-${rootProject.version}.jar")

        from(zipTree(desktopJarPath), zipTree(androidJarPath))

        doLast {
            delete(desktopJarPath, androidJarPath)
        }
    }
}

// genesis 子项目配置
project(":genesis") {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    configure<SourceSetContainer> {
        named("main") {
            java.srcDirs("genesis/src")
            resources.srcDirs("assets/genesis")
        }
    }

    dependencies {
        implementation(project(":library"))
        implementation(project(":"))
    }

    tasks.named<Jar>("jar") {
        val modNameG = rootProject.extra["modNameG"] as String
        archiveFileName.set("${modNameG}Desktop.jar")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(configurations.runtimeClasspath.get().map {
            if (it.isDirectory) it else zipTree(it)
        })

        from(projectDir) {
            include("genesis/mod.hjson")
        }

        from("../assets/genesis/") {
            include("**")
        }

        dependsOn(configurations.runtimeClasspath)
    }

    tasks.register("jarAndroid") {
        dependsOn("jar")

        doLast {
            val sdkRoot = System.getenv("ANDROID_HOME") ?: System.getenv("ANDROID_SDK_ROOT")

            if (sdkRoot.isNullOrBlank() || !File(sdkRoot).exists()) {
                throw GradleException("No valid Android SDK found. Ensure that ANDROID_HOME is set to your Android SDK directory.")
            }

            val platformRoot = File("$sdkRoot/platforms/").listFiles()?.sortedDescending()?.find { f ->
                File(f, "android.jar").exists()
            }

            if (platformRoot == null) {
                throw GradleException("No android.jar found. Ensure that you have an Android platform installed.")
            }

            val dependencies = (configurations.compileClasspath.get().toList() +
                    configurations.runtimeClasspath.get().toList() +
                    listOf(File(platformRoot, "android.jar"))).joinToString(" ") {
                "--classpath ${it.path}"
            }

            val isWindows = System.getProperty("os.name").lowercase().contains("windows")
            val d8 = if (isWindows) "d8.bat" else "d8"
            val modNameG = rootProject.extra["modNameG"] as String

            val libsDir = layout.buildDirectory.dir("libs").get().asFile
            val command = mutableListOf<String>()
            command.add(d8)
            command.addAll(dependencies.split(" ").filter { it.isNotBlank() })
            command.addAll(
                listOf(
                    "--min-api",
                    "14",
                    "--output",
                    "${modNameG}Android.jar",
                    "${modNameG}Desktop.jar"
                )
            )

            ProcessBuilder(command)
                .directory(libsDir)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start()
                .waitFor()
        }
    }

    tasks.register<Jar>("deploy") {
        dependsOn("jarAndroid", "jar")

        val modNameG = rootProject.extra["modNameG"] as String
        val libsDir = layout.buildDirectory.dir("libs").get().asFile
        val desktopJarPath = File(libsDir, "${modNameG}Desktop.jar")
        val androidJarPath = File(libsDir, "${modNameG}Android.jar")

        archiveFileName.set("$modNameG-${rootProject.version}.jar")

        from(zipTree(desktopJarPath), zipTree(androidJarPath))

        doLast {
            delete(desktopJarPath, androidJarPath)
        }
    }
}

// 全局任务
tasks.register("buildAll") {
    group = "build"
    description = "构建所有子项目"
    dependsOn(":library:build", ":genesis:build", "build")
}

tasks.register("jarAll") {
    group = "build"
    description = "打包所有子项目的JAR文件"
    dependsOn(":library:jar", ":genesis:jar", "jar")
}

tasks.register("jarAndroidAll") {
    group = "build"
    description = "打包所有子项目的Android JAR文件"
    dependsOn(":library:jarAndroid", ":genesis:jarAndroid", "jarAndroid")
}

tasks.register("deployAll") {
    group = "build"
    description = "部署所有子项目的JAR文件"
    dependsOn(":library:deploy", ":genesis:deploy", "deploy")
}

tasks.register("cleanAll") {
    group = "build"
    description = "清理所有子项目的构建文件"
    dependsOn(":library:clean", ":genesis:clean", "clean")
}
