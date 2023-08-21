plugins {
    id("com.android.application")
    id("kotlin-android")
}

val packageName = "rocka.template"

val javaVersion = JavaVersion.VERSION_11

android {
    namespace = packageName
    compileSdk = 33
    buildToolsVersion = "33.0.2"

    defaultConfig {
        applicationId = packageName
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "0.0.1"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
}

// https://youtrack.jetbrains.com/issue/KT-55947
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = javaVersion.toString()
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
}
