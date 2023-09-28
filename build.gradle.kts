import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "ru.yusdm.friday"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.13.8")
	testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")

	testImplementation(kotlin("test-junit5"))
	testImplementation("com.appmattus.fixture:fixture:1.2.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
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
