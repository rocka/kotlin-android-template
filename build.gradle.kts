plugins {
    id("com.android.application") version "8.12.0" apply false
    kotlin("android") version "2.2.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
