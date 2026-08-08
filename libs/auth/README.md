# libs/auth

Módulo de **autenticação** do toolkit. Fornece fluxo completo de login com Google via Firebase
Authentication, com tela pronta em Jetpack Compose.

## Visão Geral

- **Package:** `com.vald3nir.toolkit.auth`
- **Depende de:** `:toolkit:core`
- **Plugins:** `toolkit.module`, `toolkit.di.hilt`, `toolkit.firebase`

## Estrutura de Pacotes

```
auth/
├── domain/
│   └── AuthenticatedUserDTO.kt      # Modelo de dados do usuário autenticado
├── repository/
│   ├── AuthenticatedUserRepository.kt  # Interface do repositório de autenticação
│   ├── FirebaseAuthenticator.kt        # Implementação de auth via Firebase
│   └── GoogleAuthenticator.kt          # Autenticação Google via Credential Manager
└── presentation/
    ├── AuthViewModel.kt                # ViewModel do fluxo de autenticação
    ├── AuthScreen.kt                   # Tela de login (Composable)
    └── AuthRouter.kt                   # Roteamento pós-autenticação
```

## Funcionalidades

- **Login com Google**
  via [Credential Manager API](https://developer.android.com/identity/sign-in/credential-manager) (
  padrão moderno sem WebView)
- **Firebase Authentication** como backend de identidade
- **Nonce seguro** com hash SHA-256 para proteção contra replay attacks
- **Tela de login pronta** com botão Google, informações de login e links para Termos de Uso e
  Política de Privacidade
- **Estado de conectividade** — exibe alerta quando o dispositivo está offline
- **Injeção de dependência** via Hilt

## API Pública

### `AuthenticatedUserRepository` (interface)

```kotlin
interface AuthenticatedUserRepository {
    suspend fun updateAuthenticatedUser(authenticatedUser: AuthenticatedUserDTO?)
    suspend fun onAuthenticateWithGoogle(googleIdToken: String, uuid: UUID)
    fun loadAuthenticatedUser(): Flow<AuthenticatedUserDTO>
    suspend fun logout()
}
```

Implemente essa interface no seu módulo de dados para persistir o usuário autenticado (ex.: Room,
DataStore, Supabase).

### `GoogleAuthenticator`

```kotlin
object GoogleAuthenticator {
    suspend fun authenticate(
        context: Context,
        webGoogleClientID: String,
        uuid: UUID
    ): String // retorna o Google ID Token
}
```

### `AuthScreen` (Composable)

```kotlin
@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    appPrivacyPolicyURL: String,
    appTermsUseLink: String,
    webGoogleClientID: String,
    onSuccess: () -> Unit = {},
)
```

### `AuthenticatedUserDTO`

```kotlin
data class AuthenticatedUserDTO(
    val uid: String,
    val name: String?,
    val email: String?,
    val photoUrl: String?,
    // ...
)
```

## Uso

### 1. Adicionar a dependência

```kotlin
// build.gradle.kts
dependencies {
    implementation(project(":toolkit:libs:auth"))
}
```

### 2. Implementar o repositório

```kotlin
@HiltViewModel
class AuthViewModel @Inject constructor(
    parameters: BaseViewModelParameters,
    private val repository: AuthenticatedUserRepository, // sua implementação
) : AuthViewModel(parameters, repository)
```

### 3. Exibir a tela de login

```kotlin
AuthScreen(
    appPrivacyPolicyURL = "https://seuapp.com/privacidade",
    appTermsUseLink = "https://seuapp.com/termos",
    webGoogleClientID = BuildConfig.GOOGLE_WEB_CLIENT_ID,
    onSuccess = { navController.navigate("home") }
)
```

## Dependências Externas

| Biblioteca                                       | Finalidade                              |
|--------------------------------------------------|-----------------------------------------|
| Firebase Authentication                          | Backend de identidade                   |
| `androidx.credentials:credentials`               | Credential Manager API (Google Sign-In) |
| `com.google.android.libraries.identity.googleid` | Google ID Option builder                |
| Hilt                                             | Injeção de dependência                  |
