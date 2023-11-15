plugins {
    id("Sockets.java-application-conventions")
}

dependencies {
    implementation(project(":functional-server-library"))
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation(files("libs/mysql-connector-j-8.2.0.jar"))

}

application {
    // Define the main class for the application.
    mainClass.set("app.App")
}
