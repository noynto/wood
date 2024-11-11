plugins {
    java
    jacoco
}

repositories { mavenCentral() }

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
    implementation(project(":cedar"))
}

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

tasks.named<Test>("test") {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)
}
