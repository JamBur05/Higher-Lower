plugins {
    kotlin("jvm") version "1.8.20"
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("edu.sc.seis.launch4j") version "3.0.6"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.beryx.jlink") version "2.23.6"

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

launch4j {
    headerType="console"



    mainClassName = "HigherLower.Game.Main"  // Specify the main class
    outfile = "build/libs/HigherLower.exe"  // Output EXE file

    jreMinVersion = "11"  // Minimum JRE version required
    bundledJrePath = null

    jvmOptions = listOf(
        "--module-path", "C:/Program Files/javafx-sdk-23.0.1/lib",  // Path to JavaFX libraries
        "--add-modules", "javafx.controls,javafx.fxml"  // Add JavaFX modules
    )

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

tasks {
    shadowJar {
        mergeServiceFiles()
        manifest {
            attributes(
                "Main-Class" to "HigherLower.Game.Main" // Ensure your main class is specified here
            )
        }
    }
}

