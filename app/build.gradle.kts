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

kapt {
    useBuildCache = true
}

dependencies {
    implementation(Libs.Kotlin.stdlib)
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.21")

    // Android support libraries
    implementation(Libs.AndroidX.recyclerview)
    implementation(Libs.AndroidX.Fragment.fragmentKtx)
    implementation(Libs.AndroidX.Activity.activityKtx)
    implementation(Libs.Google.material)
    implementation(Libs.AndroidX.constraintlayout)
    implementation(Libs.AndroidX.Lifecycle.extensions)
    implementation(Libs.AndroidX.Lifecycle.SavedState.savedstate)
    kapt(Libs.AndroidX.Lifecycle.compiler)

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
    compileOnly("com.squareup.inject:assisted-inject-annotations-dagger2:0.3.3")
    kapt("com.squareup.inject:assisted-inject-processor-dagger2:0.3.3")

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
