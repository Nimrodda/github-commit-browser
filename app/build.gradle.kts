import com.nimroddayan.buildsrc.Libs

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
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

dependencies {
    implementation(Libs.Kotlin.stdlib)

    // Android support libraries
    implementation(Libs.AndroidX.recyclerview)
    implementation(Libs.Google.material)
    implementation(Libs.AndroidX.constraintlayout)
    implementation(Libs.AndroidX.Lifecycle.extensions)
    kapt(Libs.AndroidX.Lifecycle.compiler)

    // Retrofit related
    implementation(Libs.Moshi.moshi)
    implementation(Libs.Moshi.codegen)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.scalars)
    implementation(Libs.Retrofit.moshi)
    implementation(Libs.Retrofit.rxjava)
    implementation(Libs.OkHttp.okhttp)
    debugImplementation(Libs.OkHttp.okhttp)


    // Dagger related
    kapt(Libs.Dagger.compiler)
    kapt(Libs.Dagger.androidProcessor)
    implementation(Libs.Dagger.dagger)
    implementation(Libs.Dagger.androidSupport)

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

    testImplementation(Libs.junit)
}
