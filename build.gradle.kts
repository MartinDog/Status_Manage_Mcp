plugins {
    id("java")
    id("org.springframework.boot") version "3.3.4" // 버전을 3.3.4로 고정
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.active"
version = "1.0-SNAPSHOT"
val springAiVersion = "1.0.0-M3" // 변수 선언 방식
repositories {
    mavenCentral()
    gradlePluginPortal()
    maven { url = uri("https://repo.spring.io/milestone") } // Spring AI용
    maven { url = uri("https://repo.spring.io/snapshot") }
}
dependencyManagement {
    imports {
        mavenBom("org.springframework.ai:spring-ai-bom:$springAiVersion")
    }
}
dependencies {
    // --- 1. Core Web & Actuator ---
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // --- 2. Kafka (Messaging) ---
    implementation("org.springframework.kafka:spring-kafka")

    // --- 3. Redis & Vector Store (Memory) ---
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.ai:spring-ai-redis-store-spring-boot-starter")

    // --- 4. AI & Agent (Brain) ---
    implementation("org.springframework.ai:spring-ai-vertex-ai-gemini-spring-boot-starter")
    // --- 5. Utilities (Lombok) ---
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // --- 6. Testing (Testcontainers) ---
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:kafka")
    testImplementation("org.testcontainers:testcontainers:1.20.1")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}