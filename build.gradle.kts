import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
    id("org.sonarqube") version "5.1.0.4882"
    id("jacoco")
}

group = "br.group.twenty.challenge.product"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "br.group.twenty.challenge.product.ProductApplication"
    }
}

jacoco {
    toolVersion = "0.8.8"
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")

    //Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

sonarqube {
    properties {
        property("sonar.projectKey", "product-service")
        property("sonar.host.url", "http://localhost:9000") // URL do seu servidor SonarQube
        property("sonar.login", "sqp_ff8ce4fa94e5c3b0a66e21dce5b9fa9f0e756be2") // Token gerado no SonarQube
        property("sonar.kotlin.language.level", "1.9") // Versão do Kotlin
        property("sonar.sources", "src/main/kotlin") // Diretório de fontes
        property("sonar.tests", "src/test/kotlin") // Diretório de testes
        property("sonar.junit.reportsPath", "/Users/moraes/IdeaProjects/product-service/build/test-logs") // Caminho dos relatórios de teste
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
    useJUnitPlatform()  // Para usar JUnit 5 (caso esteja utilizando)
    testLogging {
        events("passed", "failed", "skipped")
    }
    reports {
        junitXml.required.set(true)
        junitXml.outputLocation.set(file("/Users/moraes/IdeaProjects/product-service/build/test-results/test"))
        junitXml.setDestination(file("/Users/moraes/IdeaProjects/product-service/build/test-results/test"))
        html.required.set(true)
        html.outputLocation.set(file("/Users/moraes/IdeaProjects/product-service/build/test-results/test"))
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
        html.outputLocation.set(file("/Users/moraes/IdeaProjects/product-service/build/reports/jacoco"))
    }
}
