# designsystem

Biblioteca de **Design System** do toolkit. Centraliza todos os componentes visuais, temas,
tipografia e templates de tela utilizados nas aplicações Android do projeto.

## Visão Geral

- **Package:** `com.vald3nir.toolkit.designsystem`
- **Tecnologia:** Jetpack Compose + Material 3
- **Plugin:** `toolkit.module` (sem dependências extras — é a base de todos os módulos)

## Estrutura de Pacotes

```
designsystem/
├── annotations/          # Anotações para previews
├── components/
│   ├── buttons/          # Botões (primário, outline, texto, FAB, delete, toggle)
│   ├── calendar/         # Calendário e seletores de mês/ano
│   ├── charts/           # Gráficos (barra, linha, pizza, gauge, progresso)
│   ├── chips/            # Chips (assist, filter, input, connection)
│   ├── containers/       # Surface, Card, Background, Loading
│   ├── dialogs/          # Diálogos (alerta, input, select, date picker)
│   ├── dividers/         # Divisores visuais
│   ├── icons/            # Ícones e avatar de ícone
│   ├── image/            # Imagem assíncrona e placeholder
│   ├── inputs/           # Campos de entrada (texto, senha, monetário, inteiro, decimal, busca, autocomplete)
│   ├── itemlist/         # Item de lista
│   ├── menus/            # Dropdown, Bottom Sheet, Horizontal Selector
│   ├── navigation/       # Navigation Bar, Rail, Drawer, Suite Scaffold, Tabs
│   ├── notifications/    # Toast, Disclaimer, NotificationDot
│   ├── scrollbar/        # Scrollbar customizada para LazyList
│   ├── selectors/        # Checkbox, RadioButton, Switch, Tabs, SelectButtonGroup
│   └── texts/            # Text e Tag
├── extensions/           # PreviewUtils e ComposeExtensions
├── templates/            # Templates prontos de tela
└── theme/
    ├── brands/           # Temas de cor por brand (Blue, Green, Red, Yellow, Purple)
    ├── domain/           # Enums e DTOs de configuração de tema
    ├── providers/        # BackgroundTheme, GradientColors, TintTheme
    └── text/             # Tipografia do toolkit
```

## Tema

### Brands disponíveis

| Brand         | Classe             |
|---------------|--------------------|
| Azul (padrão) | `BlueThemeBrand`   |
| Verde         | `GreenThemeBrand`  |
| Vermelho      | `RedThemeBrand`    |
| Amarelo       | `YellowThemeBrand` |
| Roxo          | `PurpleThemaBrand` |

### Configuração de Tema

```kotlin
// ThemeSettingsDTO
data class ThemeSettingsDTO(
    val themaBrandEnum: ThemeBrandEnum = ThemeBrandEnum.BLUE,
    val darkTheme: Boolean = false,
    val disableDynamicTheming: Boolean = true,
)
```

**`UIThemeConfigEnum`** — Modos: `FOLLOW_SYSTEM`, `LIGHT`, `DARK`

### Ponto de entrada do tema

```kotlin
ToolkitTheme(themeSettings = themeSettings) {
    // conteúdo do app
}
```

## Componentes

### Botões

| Componente              | Descrição                               |
|-------------------------|-----------------------------------------|
| `ToolkitButton`         | Botão primário preenchido               |
| `ToolkitOutlinedButton` | Botão com borda                         |
| `ToolkitTextButton`     | Botão somente texto                     |
| `ToolkitFloatingButton` | FAB (Floating Action Button)            |
| `ToolkitFixedButton`    | Botão fixo na base da tela              |
| `ToolkitDeleteButton`   | Botão de exclusão com confirmação       |
| `ToolkitToggleButton`   | Botão de alternância (on/off)           |
| `ToolkitToggleView`     | Alternância visual entre dois conteúdos |
| `ToolkitAlertButton`    | Botão de alerta/destaque                |

### Inputs

| Componente                 | Descrição                                         |
|----------------------------|---------------------------------------------------|
| `ToolkitInputText`         | Campo de texto genérico                           |
| `ToolkitInputPassword`     | Campo de senha com toggle de visibilidade         |
| `ToolkitInputMonetary`     | Campo monetário com máscara                       |
| `ToolkitInputInteger`      | Campo numérico inteiro                            |
| `ToolkitInputDecimal`      | Campo numérico decimal (formato `0,0`, sem moeda) |
| `ToolkitSearchField`       | Campo de busca                                    |
| `ToolkitAutoCompleteInput` | Campo com sugestões de autocomplete               |

### Seletores

| Componente                 | Descrição                   |
|----------------------------|-----------------------------|
| `ToolkitCheckbox`          | Caixa de seleção            |
| `ToolkitRadioButton`       | Botão de opção              |
| `ToolkitRadioButtonGroup`  | Grupo de radio buttons      |
| `ToolkitSwitch`            | Toggle switch               |
| `ToolkitSwitchLabel`       | Switch com label            |
| `ToolkitTabs`              | Abas horizontais            |
| `ToolkitSelectButtonGroup` | Grupo de botões de seleção  |
| `SelectionOptions`         | Opções de seleção genéricas |

### Gráficos

| Componente             | Descrição                     |
|------------------------|-------------------------------|
| `ToolkitBarChart`      | Gráfico de barras             |
| `ToolkitLineChart`     | Gráfico de linhas             |
| `ToolkitPieChart`      | Gráfico de pizza              |
| `ToolkitGaugeChart`    | Gráfico gauge (velocímetro)   |
| `ToolkitProgressChart` | Gráfico de progresso circular |

### Diálogos

| Componente                | Descrição                    |
|---------------------------|------------------------------|
| `ToolkitAlertDialog`      | Diálogo de alerta com ações  |
| `ToolkitInputTextDialog`  | Diálogo com campo de entrada |
| `ToolkitSelectDialog`     | Diálogo de seleção de itens  |
| `ToolkitDatePickerDialog` | Seletor de data              |

### Navegação

| Componente                       | Descrição                             |
|----------------------------------|---------------------------------------|
| `ToolkitNavigationBar`           | Barra de navegação inferior           |
| `ToolkitNavigationRail`          | Rail lateral de navegação             |
| `ToolkitNavigationDrawer`        | Drawer lateral                        |
| `ToolkitNavigationSuiteScaffold` | Scaffold adaptativo (bar/rail/drawer) |
| `ToolkitTabContent`              | Conteúdo tabulado                     |

### Containers

| Componente            | Descrição                        |
|-----------------------|----------------------------------|
| `ToolkitSurface`      | Surface com suporte a tema       |
| `ToolkitCard`         | Cartão com elevação              |
| `ToolkitBackground`   | Background temático              |
| `ToolkitLoading`      | Indicador de carregamento        |
| `ToolkitLoadingWheel` | Spinner circular de carregamento |

### Chips

| Componente              | Descrição                 |
|-------------------------|---------------------------|
| `ToolkitAssistChip`     | Chip de assistência       |
| `ToolkitFilterChip`     | Chip de filtro            |
| `ToolkitInputChip`      | Chip de entrada           |
| `ToolkitConnectionChip` | Chip de status de conexão |

### Calendário

| Componente                 | Descrição            |
|----------------------------|----------------------|
| `ToolkitCalendar`          | Calendário completo  |
| `ToolkitSelectorMonthYear` | Seletor de mês e ano |
| `ToolkitSelectorYear`      | Seletor de ano       |

### Top Bars

| Componente                | Descrição                            |
|---------------------------|--------------------------------------|
| `ToolkitTopBar`           | Barra superior padrão                |
| `ToolkitTopBarWithAvatar` | Barra superior com avatar de usuário |

### Menus

| Componente                  | Descrição                         |
|-----------------------------|-----------------------------------|
| `ToolkitDropdown`           | Menu dropdown                     |
| `ToolkitBottomSheet`        | Bottom sheet modal                |
| `ToolkitHorizontalSelector` | Seletor horizontal tipo carrossel |

### Notificações

| Componente               | Descrição                           |
|--------------------------|-------------------------------------|
| `ToolkitToast`           | Toast customizado em Compose        |
| `ToolkitDisclaimer`      | Banner de aviso/disclaimer com link |
| `ToolkitNotificationDot` | Ponto de notificação (badge)        |

### Scrollbar

| Componente / Utilitário  | Descrição                                   |
|--------------------------|---------------------------------------------|
| `ToolkitScrollbar`       | Scrollbar customizada                       |
| `AppScrollbars`          | Variantes de scrollbar para LazyColumn/Row  |
| `ScrollbarExt`           | Extension functions para scrollbars         |
| `LazyScrollbarUtilities` | Utilitários para sincronização com LazyList |

### Ícones

| Componente           | Descrição                                        |
|----------------------|--------------------------------------------------|
| `ToolkitIcon`        | Ícone themable                                   |
| `ToolkitIconAvatar`  | Ícone em formato de avatar circular              |
| `ToolkitIconCatalog` | Catálogo centralizado de ícones (Material Icons) |

### Imagens

| Componente           | Descrição                             |
|----------------------|---------------------------------------|
| `ToolkitAsyncImage`  | Imagem assíncrona com Coil 3          |
| `ToolkitPlaceholder` | Placeholder enquanto a imagem carrega |

### Textos

| Componente    | Descrição                                 |
|---------------|-------------------------------------------|
| `ToolkitText` | Texto com estilos tipográficos do toolkit |
| `ToolkitTag`  | Tag/label com destaque visual             |

## Templates de Tela

| Template            | Descrição                                      |
|---------------------|------------------------------------------------|
| `ToolkitScaffold`   | Scaffold base com suporte a snackbar e loading |
| `BaseContent`       | Template base para telas com conteúdo          |
| `EmptyStateScreen`  | Tela de estado vazio com ícone e mensagem      |
| `LoadingFullscreen` | Tela de carregamento fullscreen                |
| `ProfileContent`    | Template de tela de perfil de usuário          |
| `AppVersionContent` | Template para exibição da versão do app        |

## Anotações de Preview

| Anotação          | Descrição                                     |
|-------------------|-----------------------------------------------|
| `@ThemePreviews`  | Gera previews para tema light e dark          |
| `@DevicesPreview` | Gera previews para múltiplos tamanhos de tela |

## Constantes

- **`BaseDimen`** — Espaçamentos padrão: `ToolkitSpacingXs`, `ToolkitSpacingSm`, `ToolkitSpacingMd`,
  `ToolkitSpacingLg`, `ToolkitSpacingXl`

## Uso

```kotlin
// build.gradle.kts
dependencies {
    implementation(project(":toolkit:designsystem"))
}

// Tema no setContent da Activity
ToolkitTheme(themeSettings = themeSettings) {
    ToolkitScaffold {
        ToolkitButton(text = "Confirmar", onClick = { /* ... */ })
        ToolkitInputText(value = nome, onValueChange = { nome = it }, label = "Nome")
        ToolkitBarChart(data = chartData)
    }
}
```
