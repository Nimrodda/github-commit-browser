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

import com.nimroddayan.buildsrc.Build
import com.nimroddayan.buildsrc.Libs
import com.nimroddayan.buildsrc.Modules

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Build.compileSdkVersion)
    buildToolsVersion(Build.buildToolsVersion)
    defaultConfig {
        minSdkVersion(Build.minSdkVersion)
        targetSdkVersion(Build.targetSdkVersion)
        applicationId = "com.nimroddayan.commitbrowser"
        versionCode = 100000
        versionName = "1.0.0"

        buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    lintOptions {
        isCheckDependencies = true
        isAbortOnError = true
        isCheckReleaseBuilds = false
        isWarningsAsErrors = true
        isCheckAllWarnings = true
    }
}

kapt {
    useBuildCache = true
    arguments {
        arg("dagger.formatGeneratedSource", "disabled")
        arg("dagger.fastInit", "enabled")
        arg("dagger.experimentalDaggerErrorMessages", "enabled")
    }
    correctErrorTypes = true
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation(project(Modules.base))
    implementation(project(Modules.commitlist))
    implementation(project(Modules.commitdetail))
    kapt(Libs.Dagger.compiler)
    kapt(Libs.Dagger.hiltCompiler)
    kapt(Libs.Dagger.hiltAndroidxCompiler)
    implementation(Libs.Dagger.hilt)
    kapt(Libs.Glide.compiler)
    kapt(Libs.Epoxy.processor)

    testImplementation(Libs.junit)
}
