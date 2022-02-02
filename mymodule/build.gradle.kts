plugins {
    id("com.google.devtools.ksp")
    kotlin("multiplatform")
}

kotlin {
    js(IR) {
        browser {
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(project(":annotations"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jsMain by getting {
            kotlin.srcDir("build/generated/ksp/jsMain/kotlin")
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

dependencies {
    add("kspJs", project(":compiler"))
}