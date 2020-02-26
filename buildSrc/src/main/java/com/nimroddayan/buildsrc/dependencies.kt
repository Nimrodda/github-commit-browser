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

package com.nimroddayan.buildsrc

object Build {
    const val compileSdkVersion = 29
    const val buildToolsVersion = "29.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 29
}

object Modules {
    const val base = ":base"
    const val commitlist = ":commitlist"
}

object Versions {
    const val kotlin = "1.3.61"
}

object Plugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:3.6.0"
    const val playPublisherPlugin = "com.github.triplet.gradle:play-publisher:2.1.0"
    const val safeArgsPlugins =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Libs.AndroidX.Navigation.version}"
}

object Libs {
    val threeTenBp = "org.threeten:threetenbp:1.3.8"
    val threeTenBpNoTzdb = "$threeTenBp:no-tzdb"
    val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.1.2"

    val gravitySnapHelper = "com.github.rubensousa:gravitysnaphelper:2.0"

    val rxLint = "nl.littlerobots.rxlint:rxlint:1.7.3"

    val timber = "com.jakewharton.timber:timber:4.7.1"

    val appauth = "net.openid:appauth:0.7.1"

    val junit = "junit:junit:4.13"
    val robolectric = "org.robolectric:robolectric:4.2"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"

    object Google {
        val material = "com.google.android.material:material:1.2.0-alpha04"
        val firebaseCore = "com.google.firebase:firebase-core:16.0.7"
        val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.9.9"
        val gmsGoogleServices = "com.google.gms:google-services:4.2.0"
        val fabricPlugin = "io.fabric.tools:gradle:1.27.1"
    }

    object Kotlin {
        const val version = "1.3.61"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
        val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.1.1"
        val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        val rx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$version"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object AndroidX {
        val appcompat = "androidx.appcompat:appcompat:1.0.2"
        val browser = "androidx.browser:browser:1.0.0"
        val palette = "androidx.palette:palette:1.0.0"
        val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha01"
        val emoji = "androidx.emoji:emoji:1.0.0"

        object Fragment {
            private const val version = "1.2.1"
            val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Activity {
            private const val version = "1.1.0"
            const val activityKtx = "androidx.activity:activity-ktx:$version"
        }

        object Test {
            val core = "androidx.test:core:1.1.0"
            val runner = "androidx.test:runner:1.1.1"
            val rules = "androidx.test:rules:1.1.1"

            val espressoCore = "androidx.test.espresso:espresso-core:3.1.1"
        }

        val archCoreTesting = "androidx.arch.core:core-testing:2.0.0"

        object Paging {
            private const val version = "2.1.0"
            val common = "androidx.paging:paging-common:$version"
            val runtime = "androidx.paging:paging-runtime:$version"
            val rxjava2 = "androidx.paging:paging-rxjava2:$version"
        }

        val preference = "androidx.preference:preference:1.1.0-alpha02"

        val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"

        val coreKtx = "androidx.core:core-ktx:1.0.1"

        object Lifecycle {
            private const val version = "2.2.0"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val savedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
            const val reactive = "androidx.lifecycle:lifecycle-reactivestreams:$version"
            const val common = "androidx.lifecycle:lifecycle-common-java8:$version"
        }

        object Room {
            private const val version = "2.0.0"
            val common = "androidx.room:room-common:$version"
            val runtime = "androidx.room:room-runtime:$version"
            val rxjava2 = "androidx.room:room-rxjava2:$version"
            val compiler = "androidx.room:room-compiler:$version"
        }

        object Work {
            private const val version = "1.0.0-rc01"
            val runtimeKtx = "android.arch.work:work-runtime-ktx:$version"
        }

        object Navigation {
            const val version = "2.3.0-alpha01"
            private const val group = "androidx.navigation"
            const val fragment = "$group:navigation-fragment-ktx:$version"
            const val ui = "$group:navigation-ui-ktx:$version"
        }
    }

    object RxJava {
        val rxJava = "io.reactivex.rxjava2:rxjava:2.2.6"
        val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.3.0"
        val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    }

    object Dagger {
        private const val version = "2.26"
        val dagger = "com.google.dagger:dagger:$version"
        val androidSupport = "com.google.dagger:dagger-android-support:$version"
        val compiler = "com.google.dagger:dagger-compiler:$version"
        val androidProcessor = "com.google.dagger:dagger-android-processor:$version"
    }

    object Glide {
        private const val version = "4.11.0"
        val glide = "com.github.bumptech.glide:glide:$version"
        val compiler = "com.github.bumptech.glide:compiler:$version"
        val okhttp = "com.github.bumptech.glide:okhttp3-integration:$version"
        val recyclerview = "com.github.bumptech.glide:recyclerview-integration:$version"
    }

    object Retrofit {
        private const val version = "2.7.1"
        val retrofit = "com.squareup.retrofit2:retrofit:$version"
        val moshi = "com.squareup.retrofit2:converter-moshi:$version"
        val scalars = "com.squareup.retrofit2:converter-scalars:$version"
        val rxjava = "com.squareup.retrofit2:adapter-rxjava:$version"
    }

    object OkHttp {
        private const val version = "4.4.0"
        val okhttp = "com.squareup.okhttp3:okhttp:$version"
        val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Epoxy {
        private const val version = "3.9.0"
        val epoxy = "com.airbnb.android:epoxy:$version"
        val paging = "com.airbnb.android:epoxy-paging:$version"
        val dataBinding = "com.airbnb.android:epoxy-databinding:$version"
        val processor = "com.airbnb.android:epoxy-processor:$version"
    }

    object AssistedInject {
        private const val version = "0.5.2"
        val annotationDagger2 = "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
        val processorDagger2 = "com.squareup.inject:assisted-inject-processor-dagger2:$version"
    }

    object Moshi {
        private const val version = "1.9.2"
        val moshi = "com.squareup.moshi:moshi:$version"
        val codegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }
}
