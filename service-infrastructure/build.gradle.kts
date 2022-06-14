plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGER_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN_VERSION
    kotlin("plugin.jpa") version PluginVersions.JPA_PLUGIN_VERSION
}

dependencies {
    implementation(Dependencies.REACTIVE_HIBERNATE)
    implementation(Dependencies.REACTIVE_JDSL)
    implementation(Dependencies.SPRING_DATA_COMMON)
    implementation(Dependencies.REACTIVE_MYSQL)
    implementation(Dependencies.MUTINY_KOTLIN)
    implementation(Dependencies.MUTINY_REACTOR)
    implementation(Dependencies.SPRING_SECURITY)
    implementation(Dependencies.COROUTINE_REACTOR)
    implementation(Dependencies.COROUTINE_JDK)
    implementation(Dependencies.REACTOR_COROUTINE_EXTENSION)
    implementation(Dependencies.WEBFLUX)
    implementation(Dependencies.VALIDATION)
    implementation(Dependencies.JACKSON)
    implementation(Dependencies.JJWT)
    annotationProcessor(Dependencies.CONFIGURATION_PROCESSOR)
    testImplementation(Dependencies.SPRING_TEST)
    testImplementation(Dependencies.SECURITY_TEST)
    testImplementation(Dependencies.REACTOR_TEST)
    testImplementation(Dependencies.COROUTINE_TEST)
    testRuntimeOnly(Dependencies.EMBEDDED_MYSQL)
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}
