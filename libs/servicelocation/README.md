# libs/servicelocation

Módulo de **rastreamento de localização em background** do toolkit. Fornece um serviço foreground e
um worker periódico para monitorar a posição GPS do dispositivo mesmo com o app fechado.

## Visão Geral

- **Package:** `com.vald3nir.toolkit.servicelocation`
- **Plugins:** `toolkit.module`, `toolkit.di.hilt`
- **Dependência:** `play-services-location` (Google Fused Location Provider)

## Estrutura de Pacotes

```
servicelocation/
├── LocationService.kt    # Serviço foreground de localização contínua
├── LocationWorker.kt     # Worker periódico (WorkManager) que inicia o serviço
└── BootReceiver.kt       # BroadcastReceiver para reiniciar o serviço após boot
```

## Funcionalidades

- **Serviço foreground** com notificação persistente (atende ao requisito Android 8+ de background
  execution)
- **Fused Location Provider** com alta precisão (`PRIORITY_HIGH_ACCURACY`)
- **Atualizações periódicas** a cada 60 segundos (intervalo mínimo de 30 segundos)
- **Worker periódico** via WorkManager, executado a cada 15 minutos, garantindo que o serviço
  continue ativo
- **Auto-inicialização** após reinicialização do dispositivo via `BootReceiver`
- **Ciclo de vida integrado** — o serviço estende `LifecycleService` para observação segura

## Classes Principais

### `LocationService`

Serviço foreground que monitora a localização continuamente enquanto está ativo.

```kotlin
class LocationService : LifecycleService()
```

| Comportamento       | Detalhe                                              |
|---------------------|------------------------------------------------------|
| Intervalo de update | 60.000 ms (60s)                                      |
| Intervalo mínimo    | 30.000 ms (30s)                                      |
| Precisão            | `PRIORITY_HIGH_ACCURACY`                             |
| Notificação         | Canal `LocationServiceChannel` com importância `LOW` |
| Callback            | `onLocationResult` → `sendLocationToApi(lat, lon)`   |

### `LocationWorker`

Worker periódico que inicia o `LocationService` via `startForegroundService`.

```kotlin
class LocationWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params)
```

### `scheduleWork` (função de extensão)

```kotlin
fun scheduleWork(context: Context)
```

Agenda o `LocationWorker` para executar a cada **15 minutos** com política `KEEP` (não duplica se já
agendado).

### `BootReceiver`

`BroadcastReceiver` registrado para `BOOT_COMPLETED`. Reinicia o agendamento do `LocationWorker`
após reinicialização do dispositivo.

## Uso

### 1. Adicionar a dependência

```kotlin
// build.gradle.kts
dependencies {
    implementation(project(":toolkit:libs:servicelocation"))
}
```

### 2. Registrar no AndroidManifest

```xml

<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" /><uses-permission
android:name="android.permission.ACCESS_COARSE_LOCATION" /><uses-permission
android:name="android.permission.FOREGROUND_SERVICE" /><uses-permission
android:name="android.permission.FOREGROUND_SERVICE_LOCATION" /><uses-permission
android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

<service android:name="com.vald3nir.toolkit.servicelocation.LocationService"
android:foregroundServiceType="location" />

<receiver android:name="com.vald3nir.toolkit.servicelocation.BootReceiver" android:exported="true">
<intent-filter>
    <action android:name="android.intent.action.BOOT_COMPLETED" />
</intent-filter>
</receiver>
```

### 3. Implementar envio de localização

Override o método `sendLocationToApi` em uma subclasse de `LocationService` para enviar as
coordenadas à sua API ou persistir localmente.

### 4. Iniciar o agendamento

```kotlin
// Na inicialização do app ou após obter permissão de localização
scheduleWork(context)
```

## Dependências Externas

| Biblioteca                                      | Finalidade                                     |
|-------------------------------------------------|------------------------------------------------|
| `com.google.android.gms:play-services-location` | Fused Location Provider API                    |
| `androidx.work:work-runtime-ktx`                | WorkManager para agendamento periódico         |
| `androidx.lifecycle:lifecycle-service`          | `LifecycleService` para serviços com lifecycle |
