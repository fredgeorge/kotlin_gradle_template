# CLAUDE.md — kotlin_gradle_template

Project context for AI-assisted development. Keep this file updated as the project evolves.

## Purpose

A Kotlin multi-project Gradle template demonstrating clean architectural separation, modern Gradle conventions, and the Memento pattern for persistence. Intended as a starting point for new Kotlin projects. Author: Fred George (MIT License).

## Technology Stack

| Tool                  | Version                       |
|-----------------------|-------------------------------|
| Kotlin                | 2.3.20                        |
| Java                  | 25                            |
| Gradle                | 9.4.1                         |
| JUnit Jupiter         | 6.0.3                         |
| kotlinx-serialization | 1.8.1                         |
| Target IDE            | IntelliJ IDEA 2026.1 Ultimate |

## Project Structure

```
kotlin_gradle_template/
├── build.gradle.kts          # Root build — subprojects{} config, toolchain
├── settings.gradle.kts       # Module includes, centralized repo management
├── gradle.properties         # javaVersion=25, config cache, Kotlin style
├── gradle/
│   ├── libs.versions.toml    # Version catalog (single source of truth for versions)
│   └── wrapper/              # Gradle 9.4.1
├── engine/                   # Core domain logic
├── tests/                    # Behavior tests (separate module, not inside engine)
└── persistence/              # Serialization layer (Memento pattern)
```

## Module Responsibilities

### engine
Pure domain logic. No test code, no serialization concerns. Publishes to mavenLocal as `template-engine`. Contains `Rectangle` as the sample domain class, with an inner `RectangleDto` (`@Serializable`) for DTO conversion.

### tests
Dedicated module for behavior verification. Depends on `:engine`. Uses JUnit Jupiter. Kept separate from the engine to enforce testing of public interfaces only.

### persistence
Serialization layer using the **Memento pattern**. Injects behavior via **extension functions** on domain classes and their companion objects — keeping the domain model clean. Depends on `:engine`. Publishes to mavenLocal.

## Key Architectural Patterns

**Memento Pattern (GoF):** Domain objects expose `toMemento(): String` and `Companion.fromMemento(memento: String)` extension functions in the persistence module. The domain model itself has no serialization dependencies.

**DTO Pattern:** Domain classes convert to inner `@Serializable` DTO classes for serialization. Example: `Rectangle.toDto(): RectangleDto`.

**Extension functions for persistence injection:** `RectanglePersistence.kt` adds persistence capability to `Rectangle` and `Rectangle.Companion` without modifying the domain class.

**Encoding utilities:** `Encoding.kt` (persistence module) provides generic JSON + Base64 encode/decode utilities. Key instance: `defaultJson` with `prettyPrint=false`, `ignoreUnknownKeys=true`, `classDiscriminator="type"`. Supports polymorphic serialization via `SerializersModule`.

## Gradle Conventions

- **Version catalog:** All dependency versions declared in `gradle/libs.versions.toml`. Reference as `libs.xyz` in build files.
- **Java toolchain:** Configured via `javaVersion` property (from `gradle.properties`) in the root `subprojects {}` block. Controls compiler, bytecode target, and runtime uniformly.
- **Configuration cache:** Enabled (`org.gradle.configuration-cache=true`). Avoid build script side effects that break cache compatibility.
- **Kotlin code style:** `official` (enforced via `kotlin.code.style=official`).
- **Incremental compilation:** Enabled.
- **Repositories:** `mavenLocal()` and `mavenCentral()` — centrally managed in `settings.gradle.kts`.
- **No buildSrc / convention plugins:** Cross-project config handled via `subprojects {}` in the root build file.

## Build File Layout (root build.gradle.kts)

```kotlin
val javaVersion: Int by project  // sourced from gradle.properties

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    kotlin {
        jvmToolchain(javaVersion)
    }
    // ...
}
```

## Testing Conventions

- JUnit Jupiter (JUnit 5) with explicit `useJUnitPlatform()` in test task config.
- Backtick test method names (Kotlin style).
- Test module is a sibling of the engine, not nested inside it — deliberate design to test public API only.
- Persistence module has its own tests for round-trip serialization verification.

## Domain Package

`com.nrkei.project.template` — replace with actual domain package when using as a template.

## No CI Configured

No `.github/`, `.gitlab-ci.yml`, or other CI configuration present. Add before using in production.

## Common Tasks

```bash
./gradlew build               # Build all modules
./gradlew test                # Run all tests
./gradlew publishToMavenLocal # Publish engine and persistence artifacts
```
