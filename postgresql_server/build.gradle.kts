import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.3.72"
//	id("com.bmuschko.docker-spring-boot-application") version "6.7.0"
}


group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.exposed:exposed-java-time:0.38.2")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.postgresql:postgresql")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

//flyway {
//    url = "jdbc:postgresql://35.189.161.175:5432/hasura2"
//    user = "postgres"
//    password = "postgrespassword"
//    schemas = arrayOf("daniel")
//    baselineOnMigrate = true//已有資料庫的狀態下
//
////  batch = true//購買Teams才可用
////  stream = true//購買Teams才可用
//
////	資料夾路徑default結構
////	locations = arrayOf("classpath:db/migration")
////	sqlMigrationPrefix = "V"
////	sqlMigrationSeparator = "__"
////	sqlMigrationSuffixes = arrayOf(".sql")
//
//}

//docker {
//	springBootApplication {
//		baseImage.set("openjdk:11")
//		ports.set(listOf(8080))
//		images.set(setOf("awesome-spring-boot:1.115", "awesome-spring-boot:latest"))
//		jvmArgs.set(listOf("-Dspring.profiles.active=production", "-Xmx2048m"))
//	}
//}

