package com.mkkernel.manager.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkkernel.manager.ui.theme.CHIDORI_BLUE
import com.mkkernel.manager.ui.theme.SHARINGAN_RED
import com.mkkernel.manager.ui.theme.SUSANOO_PURPLE
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/** Cabecalho futurista com olho vermelho animado (arte original desenhada em Canvas) e raios azuis. */
@Composable
fun UchihaHeader(subtitle: String, modifier: Modifier = Modifier) {
    val infinite = rememberInfiniteTransition(label = "uchiha")
    val spin by infinite.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(6000, easing = LinearEasing), RepeatMode.Restart),
        label = "spin"
    )
    val pulse by infinite.animateFloat(
        initialValue = 0.35f, targetValue = 0.9f,
        animationSpec = infiniteRepeatable(tween(1800), RepeatMode.Reverse),
        label = "pulse"
    )
    val crackle by infinite.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(420, easing = LinearEasing), RepeatMode.Restart),
        label = "crackle"
    )

    val shape = RoundedCornerShape(24.dp)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(
                Brush.linearGradient(
                    listOf(Color(0xFF0A0D26), Color(0xFF1A0F3D), Color(0xFF2A0710))
                )
            )
            .border(
                1.dp,
                Brush.linearGradient(listOf(CHIDORI_BLUE, SUSANOO_PURPLE, SHARINGAN_RED)),
                shape
            )
    ) {
        Canvas(Modifier.matchParentSizeCompat()) { drawLightning(crackle, pulse) }
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(Modifier.size(84.dp)) { drawSharingan(spin, pulse) }
            Spacer(Modifier.width(18.dp))
            Column {
                Text(
                    "MKKERNEL",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Black,
                        letterSpacing = 4.sp,
                        brush = Brush.horizontalGradient(listOf(CHIDORI_BLUE, SUSANOO_PURPLE))
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

private fun Modifier.matchParentSizeCompat(): Modifier = this.fillMaxWidth().height(110.dp)

private fun DrawScope.drawSharingan(angle: Float, glow: Float) {
    val c = center
    val r = size.minDimension / 2f
    drawCircle(SHARINGAN_RED.copy(alpha = glow * 0.45f), radius = r)
    drawCircle(SHARINGAN_RED, radius = r * 0.86f)
    drawCircle(Color(0xFF8E0F1B), radius = r * 0.86f, style = Stroke(width = r * 0.06f))
    drawCircle(Color.Black, radius = r * 0.62f, style = Stroke(width = r * 0.05f))
    rotate(angle, c) {
        for (i in 0 until 3) {
            val a = i * 2 * PI / 3
            val p = Offset(c.x + (r * 0.62f * cos(a)).toFloat(), c.y + (r * 0.62f * sin(a)).toFloat())
            drawCircle(Color.Black, radius = r * 0.15f, center = p)
            val tail = Offset(
                c.x + (r * 0.62f * cos(a + 0.55)).toFloat(),
                c.y + (r * 0.62f * sin(a + 0.55)).toFloat()
            )
            drawLine(Color.Black, p, tail, strokeWidth = r * 0.09f, cap = StrokeCap.Round)
        }
    }
    drawCircle(Color.Black, radius = r * 0.17f, center = c)
}

private fun DrawScope.drawLightning(seed: Float, glow: Float) {
    val w = size.width
    val h = size.height
    val rnd = java.util.Random((seed * 6).toInt().toLong() + 7L)
    repeat(3) { n ->
        var x = w * (0.45f + 0.18f * n)
        var y = 0f
        val color = CHIDORI_BLUE.copy(alpha = 0.25f + 0.5f * glow)
        while (y < h) {
            val nx = x + (rnd.nextFloat() - 0.5f) * 34f
            val ny = y + 14f + rnd.nextFloat() * 14f
            drawLine(color, Offset(x, y), Offset(nx, ny), strokeWidth = 2.2f, cap = StrokeCap.Round)
            x = nx.coerceIn(0f, w)
            y = ny
        }
    }
}
