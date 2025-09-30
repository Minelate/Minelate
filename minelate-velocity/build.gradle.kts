plugins {
    id("java")
    id("net.kyori.blossom") version "2.1.0"
}

group = "dev.thezexquex.minelate"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.velocity)
    annotationProcessor(libs.velocity)

    implementation(project(":minelate-common"))
    implementation("org.incendo:cloud-velocity:2.0.0-beta.10")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}