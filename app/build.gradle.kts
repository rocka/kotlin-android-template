plugins {
    id("com.android.application")
    id("kotlin-android")
}

val packageName = "rocka.template"

android {
    namespace = packageName
    compileSdk = 33
    buildToolsVersion = "33.0.0"

    defaultConfig {
        applicationId = packageName
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "0.0.1"
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDefault = true
            isDebuggable = true
            isJniDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            isJniDebuggable = false
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.2.2")
}
