# Kotlin Gradle Template

This is an opinionated, minimal starter for a **multi-module Kotlin project** using the latest tool‑chain as of 2025-06-09:

* **JDK 24** via Gradle toolchains  
* **Gradle 8.14.2** (wrapper generated automatically on first run)  
* **Kotlin 2.1.21**  
* **JUnit 5.12.2** via the Jupiter API & engine  

## Structure

```
.
├── build.gradle.kts          # root build with shared config
├── settings.gradle.kts       # declares modules
├── gradle.properties
├── engine/                   # production code
│   └── src/main/kotlin/…
└── tests/                    # test module depending on engine
    └── src/test/kotlin/…
```

## Getting started

1. **Open in IntelliJ IDEA 2025.1+**  
   *File ▶ Open…* and select the project root.  
   The IDE will detect the Gradle wrapper and import the project.

2. **Run a build**  
   ```bash
   ./gradlew test
   ```
   Gradle will download JDK 24 automatically using the toolchain directive.

3. **Commit to GitHub**  
   ```bash
   git init
   git add .
   git commit -m "Initial template"
   gh repo create my-kotlin-template --public --source=. --remote=origin
   git push -u origin main
   ```

## Common IntelliJ hiccups

*If you see “multiple `FROM` statements” or duplicate class/resource errors while the same Gradle build works fine from the command line, it typically means the IDE is falling back to its own (older) bundled JDK or an embedded Gradle.*  
Check **Settings ▶ Build, Execution, Deployment ▶ Build Tools ▶ Gradle**:

* Use *Gradle JVM → JDK 24* (or “17+” if you prefer an LTS).  
* Use the “*Gradle from Wrapper*” option.

Re‑import the project (the ⟳ icon in the Gradle tool‑window) after changing these values.

## Updating versions

* Gradle: update the `distributionUrl` in `gradle/wrapper/gradle-wrapper.properties` (will appear after the first wrapper task).  
* Kotlin: change the plugin version in the root `build.gradle.kts`.  
* JUnit: change the version in `tests/build.gradle.kts`.
