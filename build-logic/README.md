# build-logic

Módulo de **convenções de build** do toolkit. Centraliza toda a configuração Gradle dos projetos
Android, eliminando duplicação de scripts em cada módulo.

## Visão Geral

O `build-logic` define plugins Gradle reutilizáveis via `convention plugins`. Cada plugin encapsula
um conjunto coeso de dependências e configurações, permitindo que módulos declarem suas capacidades
com uma única linha no `build.gradle.kts`.

- **Package:** `com.vald3nir.toolkit.buildlogic`
- **Linguagem:** Kotlin DSL (`kotlin-dsl`)
- **Java/Kotlin target:** JVM 17 / Java 11

## Environment Setup

| Configuração  | Valor |
|---------------|-------|
| `minSdk`      | 26    |
| `targetSdk`   | 37    |
| `compileSdk`  | 37    |
| `javaVersion` | 11    |

## Plugins Disponíveis

| Plugin ID             | Classe              | Finalidade                                                                                                |
|-----------------------|---------------------|-----------------------------------------------------------------------------------------------------------|
| `toolkit.application` | `ApplicationPlugin` | Configura módulos do tipo `com.android.application` com Compose, build types, product flavors e libs base |
| `toolkit.module`      | `ModulePlugin`      | Configura módulos do tipo `com.android.library` com Compose, KSP e libs base                              |
| `toolkit.di.hilt`     | `HiltPlugin`        | Adiciona Hilt (injeção de dependência) ao módulo                                                          |
| `toolkit.room`        | `RoomPlugin`        | Adiciona Room (banco de dados local) com KSP                                                              |
| `toolkit.network`     | `NetworkPlugin`     | Adiciona Retrofit, OkHttp e cliente MQTT (HiveMQ)                                                         |
| `toolkit.firebase`    | `FirebasePlugin`    | Adiciona Firebase (Crashlytics, Analytics, Performance)                                                   |
| `toolkit.supabase`    | `SupabasePlugin`    | Adiciona Supabase (auth, realtime, storage, postgrest)                                                    |

## Dependências Base (setupBaseLibs)

Todos os módulos que utilizam `toolkit.module` ou `toolkit.application` recebem automaticamente:

- **Kotlin / Coroutines** — `kotlinx-coroutines-core`, `kotlinx-coroutines-android`,
  `kotlinx-datetime`
- **DataStore** — preferências e proto DataStore
- **AndroidX Core** — `core-ktx`, `activity-ktx`, `browser`, `work-runtime-ktx`, `splashscreen`
- **Lifecycle** — ViewModel, LiveData, Runtime Compose
- **Jetpack Compose** — BOM, Navigation Compose, Material 3, Material Icons Extended, Adaptive
- **Image Loading** — Coil 3 (Compose, SVG, OkHttp)
- **Serialização** — Gson, `kotlinx-serialization-json`
- **Performance** — JankStats, ProfileInstaller, Tracing
- **Testes** — JUnit 5, MockK, Coroutines Test, Espresso

## Uso

```kotlin
// build.gradle.kts de um módulo biblioteca
plugins {
    alias(libs.plugins.toolkit.module)
    alias(libs.plugins.toolkit.di.hilt)
    alias(libs.plugins.toolkit.network)
}

// build.gradle.kts de um app
plugins {
    alias(libs.plugins.toolkit.application)
    alias(libs.plugins.toolkit.di.hilt)
    alias(libs.plugins.toolkit.firebase)
}
```

## Estrutura

```
build-logic/
└── convention/
    └── src/main/kotlin/
        ├── ApplicationPlugin.kt
        ├── ModulePlugin.kt
        ├── HiltPlugin.kt
        ├── RoomPlugin.kt
        ├── NetworkPlugin.kt
        ├── FirebasePlugin.kt
        ├── SupabasePlugin.kt
        └── com/toolkit/plugs/
            ├── AppBuildType.kt          # Debug / Release build types
            ├── BaseDependenciesSetup.kt # Dependências base compartilhadas
            ├── BaseLibVersions.kt       # Versões centralizadas das libs
            ├── EnvironmentSetup.kt      # SDK versions e Java target
            ├── KotlinAndroid.kt         # Configuração Kotlin/Android
            ├── ProjectExtensions.kt     # Extensões Gradle utilitárias
            └── EnvironmentSetup.kt      # Constantes de ambiente
```
