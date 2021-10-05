plugins {
    id("com.google.devtools.ksp")
    kotlin("multiplatform")
}

kotlin {
    js(IR) {
        browser {
            testTask {
                // "./gradlew jsBrowserTest" produces JUnit XML reports.
                // "./gradlew jsTest" is supposed to call jsBrowserTest but does NOT produce xml reports.
                // Probably an issue, should check this later, also see Jenkinsfile (current kotlin version 1.4.30).
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        binaries.executable()
    }
    jvm("android")

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }

        val jsMain by getting
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}