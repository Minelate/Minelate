plugins {
    id("java")
}

group = "de.unknowncity.minelate"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.kyori:adventure-api:4.24.0")
    implementation("net.kyori:adventure-text-minimessage:4.24.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}