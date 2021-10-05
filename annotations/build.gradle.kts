plugins {
    kotlin("multiplatform")
}

kotlin {
    js(IR) {
        browser()
    }
    jvm("android")

    sourceSets {
        val commonMain by getting
        val androidMain by getting
        val jsMain by getting
    }
}