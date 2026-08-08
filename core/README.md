# core

Módulo **central** do toolkit. Fornece as classes base, utilitários e serviços transversais que
todos os demais módulos Android do projeto utilizam.

## Visão Geral

- **Package:** `com.vald3nir.toolkit.core`
- **Depende de:** `:toolkit:designsystem`
- **Plugins:** `toolkit.module`, `toolkit.di.hilt`, `toolkit.network`, `toolkit.firebase`

## Estrutura de Pacotes

```
core/
├── baseclasses/          # Classes base para Activity, ViewModel, Application
├── services/
│   ├── analytics/        # Firebase Analytics helpers
│   ├── firebase/         # Firebase Realtime Database e File Storage
│   ├── iot/              # Cliente MQTT
│   ├── rest/             # Configuração Retrofit/OkHttp
│   ├── sync/             # WorkManager e monitores de sincronização
│   └── threads/          # Escopos de coroutines e JankStats
├── theme/                # Gerenciamento de tema (repositório, ViewModel, dialog)
└── utils/
    ├── extensions/       # Kotlin extension functions
    ├── security/         # Utilitários de segurança (SHA-256, StringCodes)
    └── validations/      # Validações de dados de usuário
```

## Classes Principais

### Base Classes

| Classe / Interface   | Descrição                                                                                                                                                                                                              |
|----------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `BaseActivity`       | Activity base com suporte a splash screen, tema dinâmico (light/dark/brand), edge-to-edge e `@AndroidEntryPoint`                                                                                                       |
| `BaseViewModel`      | ViewModel base com `uiState` (StateFlow), monitoramento de conexão, notificação de mensagens (`notifyUiMessage(String)` e `notifyUiMessage(@StringRes Int)`), navegação e `safeLaunch` para tratamento seguro de erros |
| `BaseApplication`    | Application base para inicialização do app                                                                                                                                                                             |
| `BaseUiState`        | Sealed class de estados de UI: `LoadingState`, `FinishState`, `ErrorState`                                                                                                                                             |
| `MainUiState`        | Estado principal de UI para controle de splash e tema                                                                                                                                                                  |
| `BaseExceptions`     | Hierarquia de exceções base do toolkit                                                                                                                                                                                 |
| `NavigationDelegate` | Gerencia eventos de navegação (back) via Flow                                                                                                                                                                          |
| `MessageNotifier`    | Canal de mensagens UI via Flow (snackbar/toast)                                                                                                                                                                        |

### Services

#### REST / Network

| Classe               | Descrição                                                                                 |
|----------------------|-------------------------------------------------------------------------------------------|
| `NetworkingSetup`    | Cria instâncias de `OkHttpClient` e `Retrofit` com interceptors de logging e Content-Type |
| `RestClientExecutor` | Executor utilitário para chamadas REST                                                    |
| `Interceptors`       | `ContentTypeInterceptor` e `CurlLoggingInterceptor`                                       |

#### Firebase

| Classe                 | Descrição                                                                                            |
|------------------------|------------------------------------------------------------------------------------------------------|
| `FirebaseDatabase`     | CRUD no Firebase Realtime Database (insertOrUpdate, readList, readObject) com suporte a modo offline |
| `FirebaseFileUploader` | Upload de arquivos para Firebase Storage                                                             |

#### IoT / MQTT

| Classe       | Descrição                                                                                     |
|--------------|-----------------------------------------------------------------------------------------------|
| `MQTTClient` | Cliente MQTT 5 (HiveMQ) com connect, disconnect, publish, subscribe e unsubscribe assíncronos |

#### Sync (WorkManager)

| Classe / Interface  | Descrição                                                  |
|---------------------|------------------------------------------------------------|
| `SyncManager`       | Interface com `isSyncing: Flow<Boolean>` e `requestSync()` |
| `SyncSubscriber`    | Interface para inscrição em sincronização                  |
| `BaseSyncWorker`    | Worker base para sincronização em background               |
| `DelegatingWorker`  | Worker delegador para injeção via Hilt                     |
| `WorkConstraints`   | Define restrições de rede para os workers                  |
| `WorkerInit`        | Inicializa workers via Hilt                                |
| `WorkManagerModule` | Módulo Hilt para WorkManager                               |

#### Monitores

| Classe            | Descrição                                                                         |
|-------------------|-----------------------------------------------------------------------------------|
| `NetworkMonitor`  | Monitora conectividade via `ConnectivityManager`, expõe `isOnline: Flow<Boolean>` |
| `TimeZoneMonitor` | Detecta mudanças de fuso horário do dispositivo                                   |
| `MonitorsModule`  | Módulo Hilt que provê os monitores                                                |

#### Analytics

| Classe / Interface    | Descrição                                           |
|-----------------------|-----------------------------------------------------|
| `AnalyticsHelper`     | Interface para rastreamento de eventos de analytics |
| `AnalyticsEvent`      | Modelo de evento de analytics                       |
| `AnalyticsExtensions` | Extension functions para disparar eventos           |
| `NotifyLog`           | Utilitário de log de notificações                   |

#### Threads

| Classe / Object         | Descrição                                               |
|-------------------------|---------------------------------------------------------|
| `JobScope`              | Escopo de coroutine para jobs de background             |
| `JankStatsExtensions`   | Extensões para monitoramento de performance (JankStats) |
| `ProfileVerifierLogger` | Logger do ProfileVerifier para Baseline Profiles        |
| `ThreadsModule`         | Módulo Hilt para dispatchers e escopos                  |

### Theme

| Classe / Interface     | Descrição                                                |
|------------------------|----------------------------------------------------------|
| `ThemaRepository`      | Interface do repositório de tema                         |
| `ThemaDataSource`      | DataSource que persiste o tema selecionado via DataStore |
| `ThemeModule`          | Módulo Hilt do tema                                      |
| `SelectThemeViewModel` | ViewModel para seleção de tema                           |
| `SelectThemeDialog`    | Dialog Compose para o usuário selecionar o tema          |
| `SelectThemeUiState`   | Estado do dialog de seleção de tema                      |
| `AppThemeDTO`          | DTO com dados do tema do app                             |

### Utils / Extensions

| Arquivo                  | Extensões                                                  |
|--------------------------|------------------------------------------------------------|
| `ActivityExtensions`     | `isSystemInDarkTheme()`, abertura de URLs e configurações  |
| `ContextExtensions`      | `openLinkURL()`, `openWifiSettings()`, notificações        |
| `BitmapExtensions`       | Conversão e manipulação de Bitmap                          |
| `CoroutineExtensions`    | `safeLaunch`, tratamento de erros em coroutines            |
| `DataExtensions`         | Conversões de tipos e tratamento de erros (`treatMessage`) |
| `DateExtensions`         | Formatação e parsing de datas                              |
| `JsonExtensions`         | Serialização/desserialização JSON                          |
| `NotificationExtensions` | Criação de notificações Android                            |
| `StringCodes`            | `toSha256Hash()` — hash de strings para segurança          |
| `UserValidations`        | Validação de CPF, e-mail, senha e outros campos            |

## Uso

```kotlin
// build.gradle.kts
dependencies {
    implementation(project(":toolkit:core"))
}

// Activity
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MyAppTheme(themeSettings) { /* ... */ } }
    }
}

// ViewModel
@HiltViewModel
class MyViewModel @Inject constructor(
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters) {

    fun loadData() = safeLaunch(
        action = { /* chamada suspensa */ },
        onSuccessEvent = { notifyState(BaseUiState.FinishState) }
    )
}

// REST
val api = NetworkingSetup.provideApiService<MyApi>("https://api.exemplo.com/")

// MQTT
val mqtt = MQTTClient("broker.exemplo.com")
mqtt.connect()
mqtt.subscribe("topico/dados") { json -> /* processar */ }
```
