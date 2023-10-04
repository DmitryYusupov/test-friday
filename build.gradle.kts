import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"

	kotlin("plugin.allopen") version "1.3.61"
	kotlin("plugin.jpa") version "1.4.10"
}

group = "ru.yusdm.friday"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	testImplementation("org.springframework.boot:spring-boot-docker-compose")

	implementation("org.flywaydb:flyway-core")

	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk-jvm:1.13.2")
	testImplementation("io.rest-assured:rest-assured")
	testImplementation("io.mockk:mockk:1.13.8")
	testImplementation(kotlin("test-junit5"))
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	jvmArgs = listOf(
		"--add-opens", "java.base/java.lang=ALL-UNNAMED",
		"--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED",
		"--add-opens", "java.base/java.util=ALL-UNNAMED",
		"--add-opens", "java.base/java.time=ALL-UNNAMED",
		"-Duser.timezone=GMT"
	)
	testLogging {
		exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
		showStackTraces = true
	}
	useJUnitPlatform()
}

