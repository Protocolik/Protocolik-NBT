plugins {
    kotlin("jvm") version "1.3.70"
    `maven-publish`
    maven
}

group = "com.github.protocolik"
version = "1.0.0"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("org.jetbrains.kotlinx", "kotlinx-io-jvm", "0.1.16")
    compileOnly("io.netty", "netty-buffer", "4.1.47.Final")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = base.archivesBaseName
            version = project.version.toString()
            pom {
                name.set("Protocolik-NBT")
                url.set("https://github.com/Protocolik/Protocolik-NBT")
                organization {
                    name.set("com.github.protocolik")
                    url.set("https://github.com/Protocolik")
                }
                issueManagement {
                    system.set("GitHub")
                    url.set("https://github.com/Protocolik/Protocolik-NBT/issues")
                }
                licenses {
                    license {
                        name.set("GNU General Public License v3.0")
                        url.set("https://raw.githubusercontent.com/Protocolik/Protocolik-NBT/master/LICENSE")
                        distribution.set("repo")
                    }
                }
                scm {
                    url.set("https://github.com/Protocolik/Protocolik-NBT")
                    connection.set("scm:https://github.com/Protocolik/Protocolik-NBT.git")
                    developerConnection.set("scm:git://github.com/Protocolik/Protocolik-NBT.git")
                }
                developers {
                    developer {
                        id.set("xjcyan1de")
                        name.set("XjCyan1de")
                        email.set("xjcyan1de@yandex.ru")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
            credentials {
                username = findProperty("sonatypeUsername").toString()
                username = findProperty("sonatypePassword").toString()
            }
        }
    }
}