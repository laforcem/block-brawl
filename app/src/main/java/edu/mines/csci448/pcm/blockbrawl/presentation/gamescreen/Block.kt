package edu.mines.csci448.pcm.blockbrawl.presentation.gamescreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

class Block(
    var x_pos: Float,
    var y_pos: Float,
    val squares: List<Point>,
    val color: Color,
    val isPlaced: Boolean) {
}