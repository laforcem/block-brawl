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
    //Current coordinates of the block (when it is still, not when it is being dragged)
    var x_pos: Float,
    var y_pos: Float,

    //This is an 2d array of chars that represents the shape of the block.
        //'C' represents the center of the block. Every block must have EXACTLY ONE center.
        //'X' represents a part of the block that is not the center
        //' ' represents empty space that is not part of the block.
    val shape: Array<Array<Char>>,
    val color: Color,

    //If the block is placed on the board or not.
    var isPlaced: Boolean) {

    //Coordinates of the block before it was dragged or placed onto the board
    val init_x_pos: Float = x_pos
    val init_y_pos: Float = y_pos
    var center_x = -1
    var center_y = -1

    //List of coordinates of the individual squares that make up the block, relative to the center of the block.
    val squares: List<Point> = buildSquaresFromShape()

    //This automatically generates the square coordinates from the given shape array
    fun buildSquaresFromShape(): List<Point>{
        val squares: MutableList<Point> = mutableListOf(Point(0, 0))
        shape.forEachIndexed { i, row ->
            if (row.indexOf('C') > -1){
                center_x = row.indexOf('C')
                center_y = i
            }
        }
        shape.forEachIndexed { i, row ->
            row.forEachIndexed { j, char ->
                if (char == 'X'){
                    squares.add(Point(j - center_x, i - center_y))
                }
            }
        }
        return squares
    }

}