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
    val shape: Array<Pair<Int, Int>>,
    val color: Color,
    val canvasWidth: Float,
    val canvasHeight: Float,
    val gameboard_size: Int
) {
    val WIDTH = canvasWidth / gameboard_size
}

fun DrawScope.drawBlock(block: Block, offsetX: Float, offsetY: Float){

    drawRect(
        color = block.color,
        size = Size(width = block.WIDTH, height = block.WIDTH),
        topLeft = Offset(block.x_pos + offsetX, block.y_pos + offsetY),
    )
}