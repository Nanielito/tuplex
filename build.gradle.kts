import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("java")
    id("java-library")
    id("maven-publish")
    id("signing")
}

group = "com.nan"
version = project.property("version") as String
val buildJavaVersion = providers.gradleProperty("buildJavaVersion")
    .map(String::toInt)
    .orElse(25)
val githubPackagesUrl = "https://maven.pkg.github.com/nanielito/maven-packages"

fun environmentVariableOrNull(name: String): String? =
    providers.environmentVariable(name).orNull?.takeIf(String::isNotBlank)

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(buildJavaVersion.get()))
    }
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}

tasks.named<Jar>("jar") {
    outputs.upToDateWhen {
        archiveFile.get().asFile.exists()
    }
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

    testLogging {
        events = setOf(
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED,
            TestLogEvent.FAILED
        )
        exceptionFormat = TestExceptionFormat.FULL
        showCauses = true
        showExceptions = true
        showStackTraces = true
    }

    reports {
        html.required.set(true)
        junitXml.required.set(true)
    }
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

                withXml {
                    val distributionManagement = asNode().appendNode("distributionManagement")
                    val repository = distributionManagement.appendNode("repository")
                    repository.appendNode("id", "GitHubPackages")
                    repository.appendNode("name", "GitHub nanielito Apache Maven Packages")
                    repository.appendNode("url", githubPackagesUrl)
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri(githubPackagesUrl)
            credentials {
                username = environmentVariableOrNull("GITHUB_PACKAGES_USER")
                    ?: environmentVariableOrNull("GITHUB_ACTOR")
                password = environmentVariableOrNull("GITHUB_PACKAGES_TOKEN")
                    ?: environmentVariableOrNull("GITHUB_TOKEN")
            }
        }
    }
}
