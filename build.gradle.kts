// ROOT build.gradle.kts
plugins {
    // Upgrading to 8.7.0+ for compatibility with Gradle 9
    id("com.android.application") version "8.7.0" apply false
    id("org.jetbrains.kotlin.android") version "2.0.20" apply false
    // Compose Compiler is now part of the Kotlin plugin (2.0+)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.20" apply false
}
