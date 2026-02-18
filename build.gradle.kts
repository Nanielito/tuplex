plugins {
    id("java")
    id("java-library")
    id("maven-publish")
    id("signing")
}

group = "com.nan"
version = project.property("version") as String

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("Tuplex")
                description.set("A small, dependency-free tuples library for Java")
                url.set("https://github.com/nanielito/tuplex")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("nanielito")
                        name.set("Daniel Ramirez")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/nanielito/tuplex.git")
                    developerConnection.set("scm:git:ssh://github.com/nanielito/tuplex.git")
                    url.set("https://github.com/nanielito/tuplex")
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/nanielito/tuplex")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
