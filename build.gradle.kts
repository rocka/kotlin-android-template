plugins {
    id("com.android.application") version "8.13.0" apply false
    kotlin("android") version "2.2.21" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
