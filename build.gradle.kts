/*
 * Copyright 2020 Nimrod Dayan nimroddayan.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.3.72"))
        classpath("com.android.tools.build:gradle:4.0.0")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0-rc01")
        classpath("com.jakewharton:butterknife-gradle-plugin:10.2.1")
        classpath(com.nimroddayan.buildsrc.Plugins.hiltPlugin)
    }
}

plugins {
    id("com.github.ben-manes.versions") version "0.27.0"
    id("org.jmailen.kotlinter") version "2.3.1"
}

subprojects {
    apply(plugin = "org.jmailen.kotlinter")

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
        kotlinOptions {
            // Treat all Kotlin warnings as errors
            allWarningsAsErrors = false

            // Enable experimental coroutines APIs, including Flow
            freeCompilerArgs = listOf(
                "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xuse-experimental=kotlinx.coroutines.FlowPreview",
                "-Xuse-experimental=kotlinx.coroutines.FlowPreview",
                "-Xuse-experimental=kotlin.Experimental"
            )

            // Set JVM target to 1.8
            jvmTarget = "1.8"
        }
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
}
