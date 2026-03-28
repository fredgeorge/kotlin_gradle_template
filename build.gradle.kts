/*
 * Copyright (c) 2025-26 by Fred George
 * @author Fred George  fredgeorge@acm.org
 * Licensed under the MIT License; see LICENSE file in root.
 */

import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    // Kotlin plugin will be applied in subprojects where needed
    id("org.jetbrains.kotlin.jvm") apply false
}

subprojects {
    // 1 – make sure the Kotlin-JVM plugin is applied
    apply(plugin = "org.jetbrains.kotlin.jvm")

    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain(25)
    }

    // Kotlin byte-code level: Match with jvmToolchain above
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25)
    }

    repositories {
        mavenLocal()
        mavenCentral()
    }
}
