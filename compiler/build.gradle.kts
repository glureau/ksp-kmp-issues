plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":annotations"))
    implementation("com.squareup:kotlinpoet:1.10.2") {
        exclude(module = "kotlin-reflect")
    }
    implementation("com.squareup:kotlinpoet-ksp:1.10.2")
    implementation("com.google.devtools.ksp:symbol-processing-api:1.6.0-RC-1.0.1-RC")

    testImplementation("com.github.tschuchortdev:kotlin-compile-testing-ksp:1.4.5")
    testImplementation("junit:junit:4.13.2")
    testImplementation(kotlin("test"))
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}
