

plugins {
    `java-library`
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    api("junit:junit:4.13")
    implementation("junit:junit:4.13")
    testImplementation("junit:junit:4.13")
    // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
    implementation("mysql:mysql-connector-java:8.0.20")
}

tasks.test {
    useJUnitPlatform()
}