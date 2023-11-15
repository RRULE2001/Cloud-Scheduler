plugins {
    id("Sockets.java-library-conventions")
}

dependencies {
    implementation(files("libs/mysql-connector-j-8.2.0.jar"))
    // Your might already have other librarires/dependencies here you should leave them 
    // as they are and just add your on new line
}