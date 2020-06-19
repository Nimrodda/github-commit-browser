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

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.jakewharton.butterknife")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Build.compileSdkVersion)
    buildToolsVersion(Build.buildToolsVersion)
    defaultConfig {
        minSdkVersion(Build.minSdkVersion)
        targetSdkVersion(Build.targetSdkVersion)
        buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dataBinding {
        isEnabled = true
    }
}

kapt {
    useBuildCache = true
    arguments {
        arg("dagger.formatGeneratedSource", "disabled")
        arg("dagger.fastInit", "enabled")
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    api(Libs.Kotlin.stdlib)
    api(Libs.Kotlin.reflect)

    // AndroidX libraries
    api(Libs.AndroidX.recyclerview)
    api(Libs.AndroidX.Fragment.fragmentKtx)
    api(Libs.AndroidX.Activity.activityKtx)
    api(Libs.Google.material)
    api(Libs.AndroidX.constraintlayout)
    api(Libs.AndroidX.Lifecycle.viewmodel)
    api(Libs.AndroidX.Lifecycle.livedata)
    api(Libs.AndroidX.Lifecycle.savedstate)
    api(Libs.AndroidX.Lifecycle.common)
    api(Libs.AndroidX.Navigation.fragment)
    api(Libs.AndroidX.Navigation.ui)

    // Retrofit related
    api(Libs.Moshi.moshi)
    kapt(Libs.Moshi.codegen)
    api(Libs.Retrofit.retrofit)
    api(Libs.Retrofit.scalars)
    api(Libs.Retrofit.moshi)
    api(Libs.OkHttp.okhttp)
    debugApi(Libs.OkHttp.loggingInterceptor)


    // Dagger related
    kapt(Libs.Dagger.compiler)
    kapt(Libs.Dagger.hiltCompiler)
    api(Libs.Dagger.dagger)
    implementation(Libs.Dagger.hilt) // Hilt dep needs to be added per module

    // Timber
    api(Libs.timber)

//    // LeakCanary
//    releaseImplementation("com.squareup.leakcanary:leakcanary-android-no-op:$leakcanaryVersion"
//    debugApi("com.squareup.leakcanary:leakcanary-android:$leakcanaryVersion"
//
    // Glide
    api(Libs.Glide.glide)
    api(Libs.Glide.okhttp)
    api(Libs.Glide.recyclerview)
    kapt(Libs.Glide.compiler)

    // Epoxy
    api(Libs.Epoxy.dataBinding)
    api(Libs.Epoxy.epoxy)
    kapt(Libs.Epoxy.processor)

    testImplementation(Libs.junit)
}
