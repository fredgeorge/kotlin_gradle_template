/*
 * Copyright (c) 2025-26 by Fred George
 * @author Fred George  fredgeorge@acm.org
 * Licensed under the MIT License; see LICENSE file in root.
 */

allprojects {
    // Replace with your domain/project id
    group = "com.nrkei.project.template"
    version = "0.1.0"
}

val javaVersion = providers.gradleProperty("javaVersion").map(String::toInt).get()

subprojects {
    plugins.withId("java") {
        extensions.configure<JavaPluginExtension> {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(javaVersion))
            }
        }
    }
}
