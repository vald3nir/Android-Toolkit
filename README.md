# Toolkit

Conjunto de módulos reutilizáveis para projetos Android. Fornece convenções de build, design system,
classes base, serviços e bibliotecas prontas para uso.

---

## Módulos

### `build-logic`

Centraliza toda a configuração Gradle via **convention plugins**. Elimina a duplicação de scripts
entre módulos: cada módulo declara suas capacidades com uma única linha no `build.gradle.kts`.

**Plugins disponíveis:**

| Plugin ID             | Finalidade                                                  |
|-----------------------|-------------------------------------------------------------|
| `toolkit.application` | Configura apps Android com Compose, build types e libs base |
| `toolkit.module`      | Configura módulos library com Compose e KSP                 |
| `toolkit.di.hilt`     | Adiciona Hilt (injeção de dependência)                      |
| `toolkit.room`        | Adiciona Room (banco de dados local) com KSP                |
| `toolkit.network`     | Adiciona Retrofit, OkHttp e cliente MQTT                    |
| `toolkit.firebase`    | Adiciona Firebase (Crashlytics, Analytics, Performance)     |
| `toolkit.supabase`    | Adiciona Supabase (auth, realtime, storage, postgrest)      |

> Consulte [`build-logic/README.md`](build-logic/README.md) para detalhes sobre dependências base e
> estrutura interna.

---

### `core`

Módulo central com classes base, utilitários e serviços transversais utilizados por todos os demais
módulos.

**Principais recursos:**

- `BaseActivity`, `BaseViewModel`, `BaseApplication` — classes base para a camada de apresentação
- `BaseUiState` — estados de UI: `LoadingState`, `FinishState`, `ErrorState`
- `NetworkingSetup` / `RestClientExecutor` — configuração Retrofit/OkHttp
- `FirebaseDatabase` / `FirebaseFileUploader` — CRUD e upload no Firebase
- `MQTTClient` — cliente MQTT 5 (HiveMQ) com coroutines
- `NetworkMonitor` / `TimeZoneMonitor` — monitoramento de conectividade e fuso horário
- `BaseSyncWorker` — sincronização em background via WorkManager
- `ThemaRepository` / `SelectThemeDialog` — gerenciamento de tema dinâmico (light/dark/brand)
- Extensions: `safeLaunch`, `toSha256Hash`, `DateExtensions`, `UserValidations`, entre outras

> Consulte [`core/README.md`](core/README.md) para a API completa.

---

### `designsystem`

Biblioteca de UI com todos os componentes visuais, temas e templates de tela em **Jetpack Compose +
Material 3**.

**Destaques:**

- **Tema** com 5 brands (Blue, Green, Red, Yellow, Purple), suporte a light/dark e tema dinâmico
- **Componentes** — botões, inputs, seletores, gráficos, diálogos, chips, calendário, menus,
  notificações, navegação, scrollbar, ícones e textos
- **Templates de tela** — `ToolkitScaffold`, `EmptyStateScreen`, `LoadingFullscreen`,
  `ProfileContent`
- **Anotações de preview** — `@ThemePreviews`, `@DevicesPreview`

> Consulte [`designsystem/README.md`](designsystem/README.md) para o catálogo completo de
> componentes.

---

### `libs/auth`

Fluxo completo de **autenticação com Google** via Firebase Authentication. Inclui tela de login
pronta em Compose, Credential Manager API e proteção contra replay attacks com nonce SHA-256.

**API pública principal:**

- `AuthScreen` — tela de login pronta (Composable)
- `AuthenticatedUserRepository` — interface para persistir o usuário autenticado
- `GoogleAuthenticator` — realiza o login e retorna o Google ID Token

> Consulte [`libs/auth/README.md`](libs/auth/README.md) para detalhes de implementação.

---

### `libs/camera`

Tela Compose para **escaneamento de QR Code e código de barras** via CameraX e ML Kit.

**API pública principal:**

- `CameraScannerScreen` — tela com preview da câmera e solicitação automática de permissão
- `QRCodeAnalyzer` — detecta e decodifica QR Codes
- `BarcodeAnalyzer` — detecta e decodifica códigos de barras

> Consulte [`libs/camera/README.md`](libs/camera/README.md) para exemplos de uso.

---

### `libs/servicelocation`

**Rastreamento de localização em background** via serviço foreground + WorkManager. Mantém o GPS
ativo mesmo com o app fechado e reinicia automaticamente após boot do dispositivo.

**API pública principal:**

- `LocationService` — serviço foreground com Fused Location Provider (atualização a cada 60s)
- `LocationWorker` — worker periódico (15 min) que mantém o serviço ativo
- `scheduleWork(context)` — agendamento do worker

> Consulte [`libs/servicelocation/README.md`](libs/servicelocation/README.md) para configuração do
> Manifest e permissões.

---

## Integrando o Toolkit em um Projeto Android

### 1. Incluir o `build-logic` e os módulos no `settings.gradle.kts`

```kotlin
pluginManagement {
    includeBuild("toolkit/build-logic") // inclui os convention plugins
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

// Módulos do toolkit disponíveis para o projeto
include(":toolkit:core")
include(":toolkit:designsystem")
include(":toolkit:libs:auth")
include(":toolkit:libs:camera")
include(":toolkit:libs:servicelocation")
```

### 2. Aplicar os plugins no módulo

**Módulo tipo `library`:**

```kotlin
// build.gradle.kts
plugins {
    alias(libs.plugins.toolkit.module)      // configuração base de library
    alias(libs.plugins.toolkit.di.hilt)     // opcional: injeção de dependência
    alias(libs.plugins.toolkit.network)     // opcional: Retrofit + OkHttp + MQTT
    alias(libs.plugins.toolkit.room)        // opcional: banco de dados local
}
```

**Módulo tipo `application`:**

```kotlin
// build.gradle.kts
plugins {
    alias(libs.plugins.toolkit.application) // configuração base de app
    alias(libs.plugins.toolkit.di.hilt)     // opcional: injeção de dependência
    alias(libs.plugins.toolkit.firebase)    // opcional: Firebase
    alias(libs.plugins.toolkit.supabase)    // opcional: Supabase
}
```

### 3. Adicionar as dependências desejadas

```kotlin
// build.gradle.kts
dependencies {
    implementation(project(":toolkit:core"))
    implementation(project(":toolkit:designsystem"))
    implementation(project(":toolkit:libs:auth"))
    implementation(project(":toolkit:libs:camera"))
    implementation(project(":toolkit:libs:servicelocation"))
}
```

### 4. Configurar o tema no `Activity`

```kotlin
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolkitTheme(themeSettings = themeSettings) {
                // conteúdo do app
            }
        }
    }
}
```

### 5. Criar ViewModels com a classe base

```kotlin
@HiltViewModel
class MyViewModel @Inject constructor(
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters) {

    fun loadData() = safeLaunch(
        action = { /* chamada suspensa */ },
        onSuccessEvent = { notifyState(BaseUiState.FinishState) }
    )
}
```

### 6. Versões de ambiente requeridas

| Configuração  | Valor |
|---------------|-------|
| `minSdk`      | 26    |
| `targetSdk`   | 37    |
| `compileSdk`  | 37    |
| `Java`        | 11    |
| `JDK (build)` | 17+   |
