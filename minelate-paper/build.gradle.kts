plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "3.0.0"
    id("de.eldoria.plugin-yml.bukkit") version "0.8.0"
    id("com.gradleup.shadow") version "9.0.0"
}

group = "dev.thezexquex.minelate"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/releases/")
}

dependencies {
    implementation(project(":minelate-common"))
    compileOnly(libs.paper)
    compileOnly(libs.papi)
    bukkitLibrary(libs.jackson)
    implementation("org.incendo:cloud-paper:2.0.0-beta.10")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

bukkit {
    version = rootProject.version.toString();
    name = "Minelate"
    main = "dev.thezexquex.minelate.paper.plugin.MinelatePaperPlugin"
    author = "TheZexquex"
}

tasks {
    runServer {
        jvmArgs("-Dcom.mojang.eula.agree=true")
        minecraftVersion("1.21.8")
    }
}

tasks.test {
    useJUnitPlatform()
}