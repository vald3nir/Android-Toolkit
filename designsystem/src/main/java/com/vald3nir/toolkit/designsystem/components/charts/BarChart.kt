package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

// Paleta de cores padrão utilizada caso o item não possua uma cor personalizada definida
private val BarChartPalette = listOf(
    Color(0xFF4A90E2),
    Color(0xFF50E3C2),
    Color(0xFFF5A623),
    Color(0xFFD0021B),
    Color(0xFF9013FE),
    Color(0xFF7ED321)
)

/**
 * Exibe um gráfico de barras com os dados informados.
 *
 * @param modifier Modificador aplicado ao gráfico.
 * @param title Título exibido acima do gráfico, se informado.
 * @param subtitle Subtítulo exibido abaixo do título, se informado.
 * @param data Valores, rótulos e cores das barras.
 * @param fitBarsToWidth Quando verdadeiro, ajusta todas as barras à largura disponível e desativa a rolagem horizontal.
 * @param useRoundedValue Quando verdadeiro, arredonda os valores exibidos nas barras. Por exemplo, 5625 = 5 K, 10524 = 10 M
 * @param useDecimalValue Quando verdadeiro, exibe os valores com casas decimais.
 * @param onClickBar Ação executada ao tocar numa barra, recebendo o índice do item correspondente em [data].
 */
@Composable
fun ToolkitBarChart(
    modifier: Modifier = Modifier,
    title: String? = null,
    subtitle: String? = null,
    data: List<ItemChartDTO>,
    fitBarsToWidth: Boolean = false,
    useRoundedValue: Boolean = true,
    useDecimalValue: Boolean = true,
    onClickBar: (index: Int) -> Unit = {},
) {
    // Se a lista de dados estiver vazia, interrompe a execução para evitar erros
    if (data.isEmpty()) return

    // Ferramentas auxiliares de texto, estado de scroll e cor dos rótulos baseada no tema atual
    val textMeasurer = rememberTextMeasurer()
    val scrollState = rememberScrollState()
    val labelsColor = MaterialTheme.colorScheme.onSurface

    // Constantes de layout para o dimensionamento das barras e do gráfico
    val barWidthFixed = 60.dp
    val maxHeightChart = 350.dp
    val barSpacing = 15.dp
    val totalBarWidth = barWidthFixed + barSpacing

    // Estrutura principal vertical contendo título, subtítulo e a área de desenho do gráfico
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = ToolkitSpacingMd)
    ) {
        // Exibe o título se ele não for nulo
        if (title != null) {
            ToolkitText(text = title, style = ToolkitTextStyle.TitleMedium)
        }
        // Exibe o subtítulo se ele não for nulo
        if (subtitle != null) {
            ToolkitText(text = subtitle, style = ToolkitTextStyle.BodySmall)
        }

        // Caixa contenedora que gerencia a rolagem horizontal (caso fitBarsToWidth seja falso)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(if (fitBarsToWidth) Modifier else Modifier.horizontalScroll(scrollState))
        ) {
            // Componente de desenho customizado (Canvas) onde as barras e textos são renderizados manualmente
            Canvas(
                modifier = Modifier
                    .then(
                        if (fitBarsToWidth) {
                            Modifier
                                .fillMaxWidth()
                                .height(maxHeightChart)
                        } else {
                            Modifier
                                .height(maxHeightChart)
                                .size(width = totalBarWidth * data.size, height = maxHeightChart)
                        }
                    )
                    // Detecta toques do usuário no canvas para identificar qual barra foi pressionada
                    .pointerInput(data, fitBarsToWidth) {
                        detectTapGestures { offset ->
                            val barSlotWidth = if (fitBarsToWidth) {
                                size.width.toFloat() / data.size
                            } else {
                                totalBarWidth.toPx()
                            }
                            // Calcula o índice da barra com base na coordenada X do toque
                            val index = (offset.x / barSlotWidth).toInt()
                            if (index in data.indices) onClickBar(index)
                        }
                    }
            ) {
                val canvasHeight = size.height
                val bottomPadding = 80f // Espaço reservado na parte inferior para os rótulos das barras
                val topPadding = 60f    // Espaço reservado na parte superior para os valores numéricos
                val chartHeight = canvasHeight - bottomPadding - topPadding

                // Encontra o maior valor nos dados para calcular a escala proporcional (evita divisão por zero)
                val maxValue = data.maxOf { it.value }.coerceAtLeast(1.0f)
                val barSlotWidth = size.width / data.size

                // Define o espaçamento e a largura efetiva de cada barra dependendo do modo de ajuste
                val effectiveBarSpacing = if (fitBarsToWidth) {
                    minOf(barSpacing.toPx(), barSlotWidth * 0.2f)
                } else {
                    barSpacing.toPx()
                }
                val barWidth = if (fitBarsToWidth) {
                    barSlotWidth - effectiveBarSpacing
                } else {
                    barWidthFixed.toPx()
                }

                // Itera sobre cada item de dado para desenhar sua respectiva barra e textos
                data.forEachIndexed { index, item ->
                    val xStart = if (fitBarsToWidth) {
                        index * barSlotWidth + (effectiveBarSpacing / 2)
                    } else {
                        index * totalBarWidth.toPx() + (effectiveBarSpacing / 2)
                    }

                    // Calcula a altura normalizada da barra com base no valor proporcional ao maior item
                    val normalizedHeight = (item.value / maxValue) * chartHeight
                    val topY = (canvasHeight - bottomPadding) - normalizedHeight
                    val baseY = canvasHeight - bottomPadding

                    // Define a cor base da barra (usa a cor do item ou busca na paleta cíclica)
                    val baseColor = item.color ?: BarChartPalette[index % BarChartPalette.size]

                    // Desenha o retângulo da barra aplicando um gradiente vertical
                    drawRect(
                        brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(baseColor, baseColor.copy(alpha = 0.3f)),
                            startY = topY,
                            endY = baseY
                        ),
                        topLeft = Offset(xStart, topY),
                        size = androidx.compose.ui.geometry.Size(barWidth, normalizedHeight)
                    )

                    // Mede e desenha o rótulo (label) na parte inferior da barra
                    val dateText = textMeasurer.measure(
                        text = item.label,
                        style = TextStyle(fontSize = 10.sp, color = labelsColor)
                    )
                    drawText(
                        textLayoutResult = dateText,
                        topLeft = Offset(
                            x = xStart + (barWidth / 2) - (dateText.size.width / 2),
                            y = baseY + 15f
                        )
                    )

                    // Mede e desenha o valor numérico formatado logo acima da barra
                    val formattedText = when {
                        !useRoundedValue -> if (useDecimalValue) item.value.toString() else item.value.toInt().toString()
                        else -> formatChartValue(item.value) // Funciona tanto para Float quanto para Int devido à função genérica Number
                    }
                    val valueText = textMeasurer.measure(
                        text = formattedText,
                        style = TextStyle(fontSize = 11.sp, color = labelsColor, fontWeight = FontWeight.Bold)
                    )
                    drawText(
                        textLayoutResult = valueText,
                        topLeft = Offset(
                            x = xStart + (barWidth / 2) - (valueText.size.width / 2),
                            y = topY - 40f
                        )
                    )
                }

                // Desenha uma linha de base horizontal na parte inferior do gráfico
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, canvasHeight - bottomPadding),
                    end = Offset(size.width, canvasHeight - bottomPadding),
                    strokeWidth = 2f
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 300.dp)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(ToolkitSpacingMd),
            contentAlignment = Alignment.Center
        ) {
            ToolkitBarChart(
                fitBarsToWidth = true,
                title = "Titulo",
                data = listOf(
                    ItemChartDTO(750f, "Jan"),
                    ItemChartDTO(200f, "Fev"),
                    ItemChartDTO(300f, "Mar"),
                    ItemChartDTO(400f, "Abr"),
                    ItemChartDTO(500f, "Mai"),
                )
            )
        }
    }
}