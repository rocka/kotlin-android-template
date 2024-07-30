import com.android.build.gradle.tasks.PackageApplication
import org.gradle.api.internal.provider.Providers
import java.lang.reflect.Field

plugins {
    id("com.android.application")
    kotlin("android")
}

val packageName = "rocka.template"

val javaVersion = JavaVersion.VERSION_1_8

android {
    namespace = packageName
    compileSdk = 35
    buildToolsVersion = "35.0.0"

    defaultConfig {
        applicationId = packageName
        minSdk = 26
        targetSdk = 35
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

    androidResources {
        @Suppress("UnstableApiUsage")
        generateLocaleConfig = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }

    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }

    buildTypes {
        onEach {
            // remove META-INF/version-control-info.textproto
            @Suppress("UnstableApiUsage")
            it.vcsInfo.include = false
        }
    }

    packaging {
        resources {
            excludes += setOf(
                "/META-INF/*.version",
                "/META-INF/*.kotlin_module",  // cannot be excluded actually
                "/kotlin/**",
                "/kotlin-tooling-metadata.json"
            )
        }
    }
}

// remove META-INF/com/android/build/gradle/app-metadata.properties
tasks.withType<PackageApplication> {
    var javaClass: Class<*>? = appMetadata.javaClass
    var valueField: Field? = null
    while (javaClass != null) {
        valueField = javaClass.declaredFields.find { it.name == "value" }
        if (valueField != null) break
        else javaClass = javaClass.superclass
    }
    valueField?.isAccessible = true
    doFirst {
        valueField?.set(appMetadata, Providers.notDefined<RegularFile>())
        allInputFilesWithNameOnlyPathSensitivity.removeAll { true }
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
}
