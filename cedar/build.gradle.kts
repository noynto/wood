plugins { java }

repositories { mavenCentral() }

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

tasks.named<Test>("test") { useJUnitPlatform() }
