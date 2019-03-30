buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.0-rc02")
        classpath(kotlin("gradle-plugin", version = "1.3.21"))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.0.0")
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
