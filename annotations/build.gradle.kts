plugins {
    kotlin("multiplatform")
}

kotlin {
    js(LEGACY) {
        browser()
    }
    jvm("android")

    sourceSets {
        val commonMain by getting
        val androidMain by getting
        val jsMain by getting
    }
}