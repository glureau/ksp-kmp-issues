plugins {
    id("com.google.devtools.ksp")
    kotlin("multiplatform")
}

kotlin {
    js(LEGACY) {
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
                implementation(project(":mymodule"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
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

dependencies {
    add("kspJs", project(":compiler"))
}
