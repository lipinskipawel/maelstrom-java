plugins {
    id("java-library")
    id("maven-publish")
    id("signing")
    id("io.github.gradle-nexus.publish-plugin") version ("1.3.0")
}

repositories {
    mavenLocal()
    mavenCentral()
}

group = "com.github.lipinskipawel"
version = "0.2.0"
description = "maelstrom-java"

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.2")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.4.1")
}

nexusPublishing {
    repositories {
        sonatype()
    }
}

publishing {
    publications {
        publications.create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name.set("maelstrom-java")
                description.set("Unofficial java client for Maelstrom server")
                url.set("https://github.com/lipinskipawel/maelstrom-java")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
                    }
                }
                developers {
                    developer {
                        name.set("Pawel Lipinski")
                    }
                }
                scm {
                    connection.set("scm:git:ssh://git@github.com/lipinskipawel/maelstrom-java.git")
                    developerConnection.set("scm:git:ssh://git@github.com/lipinskipawel/maelstrom-java.git")
                    url.set("git@github.com:lipinskipawel/maelstrom-java")
                }
            }
        }
    }
}

signing {
    val signingKeyId: String? by project
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    sign(publishing.publications["mavenJava"])
}

tasks {
    java {
        withJavadocJar()
        withSourcesJar()
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    test {
        useJUnitPlatform()
    }

    wrapper {
        gradleVersion = "7.4.2"
        distributionType = Wrapper.DistributionType.ALL
    }
}
