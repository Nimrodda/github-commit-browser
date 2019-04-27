/*
 * Copyright 2019 Nimrod Dayan nimroddayan.com
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

import com.nimroddayan.buildsrc.Libs

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jmailen.kotlinter")
}

android {
    compileSdkVersion(28)
    buildToolsVersion("28.0.3")
    defaultConfig {
        applicationId = "org.codepond.commitbrowser"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 100000
        versionName = "1.0.0"

        buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dataBinding {
        isEnabled = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

kapt {
    useBuildCache = true
    arguments {
        arg("dagger.formatGeneratedSource", "disabled")
        arg("dagger.fastInit", "enabled")
        arg("dagger.gradle.incremental")
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Kotlin.reflect)

    // AndroidX libraries
    implementation(Libs.AndroidX.recyclerview)
    implementation(Libs.AndroidX.Fragment.fragmentKtx)
    implementation(Libs.AndroidX.Activity.activityKtx)
    implementation(Libs.Google.material)
    implementation(Libs.AndroidX.constraintlayout)
    implementation(Libs.AndroidX.Lifecycle.extensions)
    implementation(Libs.AndroidX.Lifecycle.SavedState.savedstate)
    kapt(Libs.AndroidX.Lifecycle.compiler)
    implementation(Libs.AndroidX.Navigation.fragment)
    implementation(Libs.AndroidX.Navigation.ui)
    implementation(Libs.Google.material)
    // Retrofit related
    implementation(Libs.Moshi.moshi)
    kapt(Libs.Moshi.codegen)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.scalars)
    implementation(Libs.Retrofit.moshi)
    implementation(Libs.OkHttp.okhttp)
    debugImplementation(Libs.OkHttp.loggingInterceptor)


    // Dagger related
    kapt(Libs.Dagger.compiler)
    kapt(Libs.Dagger.androidProcessor)
    implementation(Libs.Dagger.dagger)
    implementation(Libs.Dagger.androidSupport)
    compileOnly(Libs.AssistedInject.annotationDagger2)
    kapt(Libs.AssistedInject.processorDagger2)

    // Timber
    implementation(Libs.timber)

//    // LeakCanary
//    releaseImplementation("com.squareup.leakcanary:leakcanary-android-no-op:$leakcanaryVersion"
//    debugImplementation("com.squareup.leakcanary:leakcanary-android:$leakcanaryVersion"
//
    // Glide
    implementation(Libs.Glide.glide)
    implementation(Libs.Glide.okhttp)
    implementation(Libs.Glide.recyclerview)
    kapt(Libs.Glide.compiler)

    // Epoxy
    implementation(Libs.Epoxy.dataBinding)
    implementation(Libs.Epoxy.epoxy)
    kapt(Libs.Epoxy.processor)

    testImplementation(Libs.junit)
}
