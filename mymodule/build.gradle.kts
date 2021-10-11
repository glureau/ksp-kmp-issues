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

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":annotations"))
                implementation(project(":submodule"))
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
            dependencies {
                configurations["ksp"].dependencies.add(project(":compiler"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}