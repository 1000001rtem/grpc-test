plugins {
    java
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.eremin"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

extra["springGrpcVersion"] = "0.5.0"

dependencies {
    annotationProcessor("org.hibernate:hibernate-jpamodelgen:6.6.11.Final")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    implementation(project(":api:model"))

    compileOnly("org.projectlombok:lombok:1.18.36")

    implementation("org.springframework.grpc:spring-grpc-spring-boot-starter")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    compileOnly("org.hibernate:hibernate-jpamodelgen:6.6.11.Final")
    implementation("org.liquibase:liquibase-core:4.31.1")
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:testcontainers:1.19.3")
    testImplementation("org.testcontainers:postgresql:1.19.3")
    testImplementation("org.testcontainers:junit-jupiter:1.19.3")
    testImplementation("io.grpc:grpc-testing")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.grpc:spring-grpc-dependencies:${property("springGrpcVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
