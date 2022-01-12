plugins {
    id("com.google.devtools.ksp")
    kotlin("multiplatform")
}

kotlin {
    jvm()
    js(LEGACY) {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":annotations"))
            }
        }
        val commonTest by getting {
            dependencies {
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
    add("kspMetadata", project(":compiler"))
}