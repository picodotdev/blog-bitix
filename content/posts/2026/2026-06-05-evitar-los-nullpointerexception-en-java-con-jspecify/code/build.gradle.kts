import net.ltgt.gradle.errorprone.CheckSeverity
import net.ltgt.gradle.errorprone.errorprone

plugins {
    application
    id("com.diffplug.spotless") version "7.0.2"
    id("net.ltgt.errorprone") version "5.1.0"
}

...

dependencies {
    compileOnly("org.jspecify:jspecify:1.0.0")

    errorprone("com.google.errorprone:error_prone_core:2.49.0")
    errorprone("com.uber.nullaway:nullaway:0.13.6")
}

...

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))

    options.errorprone {
        ...

        check("NullAway", CheckSeverity.ERROR)
        option("NullAway:AnnotatedPackages", "io.github.picodotdev.blogbitix")
    }
}

...
