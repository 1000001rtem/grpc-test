import org.gradle.internal.util.Trie.from

plugins {
    id("java-library")
    id("com.google.protobuf") version "0.9.4"
    `maven-publish`
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

configure(subprojects) {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    dependencies {
        api("com.google.protobuf:protobuf-java:3.21.12")
        api("io.grpc:grpc-stub:1.53.0")
        api("io.grpc:grpc-protobuf:1.53.0")
        api("javax.annotation:javax.annotation-api:1.3.2")
    }

    publishing {
        publications {
            afterEvaluate {
                if (!plugins.hasPlugin(JavaGradlePluginPlugin::class)) {
                    register<MavenPublication>("mavenJava") {
                        groupId = "com.eremin.grpc"
                        artifactId = project.name
                        version = "0.0.1"
                        from(components.getByName("java"))
                    }
                }
            }
        }
        repositories {
            mavenLocal()
        }
    }
}

project("client-starter") {
    dependencies {
        api(project(":api:model"))
        api("com.google.protobuf:protobuf-java:3.21.12")
        api("org.springframework.boot:spring-boot-starter:3.4.4")
        api("net.devh:grpc-client-spring-boot-starter:3.1.0.RELEASE")
        api("org.springframework.boot:spring-boot-autoconfigure:3.4.4")

    }
}

project("model") {
    apply(plugin = "com.google.protobuf")
    protobuf {
        protoc {
            artifact = "com.google.protobuf:protoc:3.21.12"
        }
        plugins {
            create("grpc") {
                artifact = "io.grpc:protoc-gen-grpc-java:1.53.0"
            }
        }
        generateProtoTasks {
            ofSourceSet("main").forEach {
                it.plugins {
                    create("grpc")
                }
            }
        }
    }
    tasks.register<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allJava)
        dependsOn("generateProto")  // Важно: генерация proto перед упаковкой
    }
    sourceSets {
        main {
            java {
                srcDirs(
                    "build/generated/source/proto/main/java",
                    "build/generated/source/proto/main/grpc"
                )
            }
        }
    }
}