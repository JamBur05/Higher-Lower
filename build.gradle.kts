plugins {
    kotlin("jvm") version "1.8.20"
    id("org.openjfx.javafxplugin") version "0.1.0"

    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.openjfx:javafx-controls:22.0.1")
    implementation("org.openjfx:javafx-fxml:22.0.1")
}

application {
    mainClass.set("HigherLower.Game.Main")
    mainModule.set("HigherLower.Game")
}

tasks.test {
    useJUnitPlatform()
}

javafx {
    version = "22.0.1"
    modules = listOf("javafx.controls", "javafx.fxml")
}

java {
    modularity.inferModulePath.set(true)
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "HigherLower.Game.Main" // Note the use of "to" instead of ":"
        )
    }
}
