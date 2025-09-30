plugins {
    id("java")
}

group = "dev.thezexquex.minelate"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.adventure.api)
    compileOnly(libs.adventure.minimessage)
    compileOnly(libs.jackson)
    compileOnly(libs.guava)
}

tasks.test {
    useJUnitPlatform()
}