plugins {
    id("com.android.application") version "8.5.2" apply false
    kotlin("android") version "2.0.10" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
