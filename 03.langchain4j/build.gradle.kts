plugins {
    id("java")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}
val llangchain4jVersion= "1.23.0"
dependencies {
// Source: https://mvnrepository.com/artifact/dev.langchain4j/langchain4j
    implementation("dev.langchain4j:langchain4j:${llangchain4jVersion}")
    implementation("dev.langchain4j:langchain4j-open-ai:${llangchain4jVersion}")
    // Source: https://mvnrepository.com/artifact/dev.langchain4j/langchain4j-embeddings
    implementation("dev.langchain4j:langchain4j-embeddings:${llangchain4jVersion}-beta33")
    // Source: https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.6.6")


    // Source: https://mvnrepository.com/artifact/io.github.cdimascio/java-dotenv
    implementation("io.github.cdimascio:java-dotenv:5.2.2")

}
