package com.buddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State for Blinking
            var isBlinking by remember { mutableStateOf(false) }

            // Idle "Breathing" Animation (Face Expansion)
            val infiniteTransition = rememberInfiniteTransition(label = "breathing")
            val breatheScale by infiniteTransition.animateFloat(
                initialValue = 0.98f,
                targetValue = 1.02f,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ), label = "scale"
            )

            // Random Blink Timer
            LaunchedEffect(Unit) {
                while(true) {
                    delay((2000..6000).random().toLong())
                    isBlinking = true
                    delay(150)
                    isBlinking = false
                }
            }

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height
                    
                    // Apply breathing scale to the center of the face
                    val eyeHeight = if (isBlinking) 4f else (100f * breatheScale)
                    val eyeWidth = 80f * breatheScale

                    // Realistic Anime Eyes (Simple Black Ovals for now)
                    drawOval(
                        color = Color.Black,
                        topLeft = Offset(w * 0.3f, h * 0.4f),
                        size = Size(eyeWidth, eyeHeight)
                    )
                    drawOval(
                        color = Color.Black,
                        topLeft = Offset(w * 0.6f, h * 0.4f),
                        size = Size(eyeWidth, eyeHeight)
                    )

                    // Simple Happy Mouth
                    drawArc(
                        color = Color(0xFFFF6666),
                        startAngle = 0f,
                        sweepAngle = 180f,
                        useCenter = false,
                        topLeft = Offset(w * 0.45f, h * 0.6f),
                        size = Size(w * 0.1f, 40f * breatheScale)
                    )
                }
            }
        }
    }
}
