# libs/camera

Módulo de **câmera e leitura de códigos** do toolkit. Fornece uma tela Compose pronta para escaneamento de QR Code e código de barras via CameraX e ML Kit.

## Visão Geral

- **Package:** `com.vald3nir.toolkit.camera`
- **Depende de:** `:toolkit:core`
- **Plugin:** `toolkit.module`

## Estrutura de Pacotes

```
camera/
├── CameraScannerScreen.kt      # Tela Compose com preview da câmera
├── CameraScannerAnalyzer.kt    # Interface base para análise de imagens
├── QRCodeAnalyzer.kt           # Implementação de leitura de QR Code
└── BarcodeAnalyzer.kt          # Implementação de leitura de código de barras
```

## Funcionalidades

- **Tela de câmera em Jetpack Compose** com preview em tempo real usando `AndroidView` + `PreviewView` (CameraX)
- **Solicitação automática de permissão** de câmera ao abrir a tela (Accompanist Permissions)
- **Fallback visual** quando a permissão é negada
- **Leitura de QR Code** via ML Kit Barcode Scanning
- **Leitura de código de barras** via ML Kit
- **Análise assíncrona** com estratégia `STRATEGY_KEEP_ONLY_LATEST` para máximo desempenho
- **Ciclo de vida integrado** com `ProcessCameraProvider` e `LifecycleOwner`

## API Pública

### `CameraScannerScreen` (Composable)

```kotlin
@Composable
fun CameraScannerScreen(analyzer: CameraScannerAnalyzer)
```

Exibe a câmera traseira em fullscreen e passa os frames para o `analyzer`. Solicita permissão de câmera automaticamente.

### `CameraScannerAnalyzer` (interface/base)

```kotlin
abstract class CameraScannerAnalyzer(
    val onCodeDetected: (String?) -> Unit
) : ImageAnalysis.Analyzer
```

Classe base para implementar analisadores de imagem. O resultado é entregue via callback `onCodeDetected`.

### `QRCodeAnalyzer`

```kotlin
class QRCodeAnalyzer(onCodeDetected: (String?) -> Unit) : CameraScannerAnalyzer(onCodeDetected)
```

Detecta e decodifica QR Codes usando o ML Kit. Ao detectar um QR Code, chama `onCodeDetected` com o valor bruto (`rawValue`).

### `BarcodeAnalyzer`

```kotlin
class BarcodeAnalyzer(onCodeDetected: (String?) -> Unit) : CameraScannerAnalyzer(onCodeDetected)
```

Detecta e decodifica códigos de barras usando o ML Kit.

## Uso

### 1. Adicionar a dependência

```kotlin
// build.gradle.kts
dependencies {
    implementation(project(":toolkit:libs:camera"))
}
```

### 2. Escanear um QR Code

```kotlin
@Composable
fun ScannerScreen(onResult: (String) -> Unit) {
    val analyzer = remember {
        QRCodeAnalyzer { qrCode ->
            qrCode?.let { onResult(it) }
        }
    }
    CameraScannerScreen(analyzer = analyzer)
}
```

### 3. Escanear um código de barras

```kotlin
val analyzer = BarcodeAnalyzer { barcode ->
    barcode?.let { Log.d("Barcode", it) }
}
CameraScannerScreen(analyzer = analyzer)
```

### 4. Permissões no AndroidManifest

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-feature android:name="android.hardware.camera" android:required="false" />
```

## Dependências Externas

| Biblioteca                              | Versão  | Finalidade                          |
|-----------------------------------------|---------|-------------------------------------|
| `androidx.camera:camera-camera2`        | 1.6.1   | Backend Camera2 para CameraX        |
| `androidx.camera:camera-lifecycle`      | 1.6.1   | Integração com ciclo de vida        |
| `androidx.camera:camera-view`           | 1.6.1   | `PreviewView` para exibição         |
| `com.google.mlkit:barcode-scanning`     | 17.3.0  | Detecção de QR Code e barcodes      |
| `com.google.accompanist:accompanist-permissions` | 0.37.3 | Solicitação de permissões em Compose |
