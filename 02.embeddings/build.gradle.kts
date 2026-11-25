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
    // Source: https://mvnrepository.com/artifact/dev.langchain4j/langchain4j-embeddings
    implementation("dev.langchain4j:langchain4j-embeddings:${llangchain4jVersion}-beta33")
}
