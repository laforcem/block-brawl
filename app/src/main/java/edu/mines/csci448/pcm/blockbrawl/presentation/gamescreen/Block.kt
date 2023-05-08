package edu.mines.csci448.pcm.blockbrawl.presentation.gamescreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

/*
// A class that represents one block in the game
 */
class Block(
    //Starting coordinates of block CENTER, before the canvas size is known
    val x_start_coord: Int,
    val y_start_coord: Int,

    //Determines if the block should appear above or below the game board
    val appearAbove: Boolean,

    //This is an 2d array of chars that represents the shape of the block.
    val shape: Array<Array<Char>>,  //'C' represents the center of the block. Every block must have EXACTLY ONE center.
                                    //'X' represents a part of the block that is not the center
                                    //' ' represents empty space that is not part of the block.

    //Value of blocks when it comes to calculating score
    val value: Int,         //Allowable values are listed below:
                                // 5, 10, 15, 20, 30
    ) {



    //If the block is placed on the board or not.
    var isPlaced = false

    //Coordinates of the block before it was dragged or placed onto the board
    var center_x = -1
    var center_y = -1

    //List of coordinates of the individual squares that make up the block, relative to the center of the block.
    val squares: List<Point> = buildSquaresFromShape()


    //Actual position of blocks on the game board
    var x_pos = 0.0f
    var y_pos = 0.0f
    //Actual initial position of blocks
    var init_x_pos: Float = x_pos
    var init_y_pos: Float = y_pos

    //Color of block, based on value
    val color: Color = getColorFromValue()

    //Set the actual block's position once the canvas size is known
    fun setPosition(canvasWidth: Float, boardWidth: Int, bottomStart: Float){
        var verticalOffset = 0.0f
        if (!appearAbove){
            verticalOffset += bottomStart
        }

        //Calculate width of grid that is 1 block less wide & tall
        val width = canvasWidth / boardWidth

        //Place blocks in that grid
        x_pos = x_start_coord*width + width/2
        y_pos = y_start_coord*width + verticalOffset + width/2

        init_x_pos = x_pos
        init_y_pos = y_pos
    }

    //This automatically generates the square coordinates from the given shape array
    private fun buildSquaresFromShape(): List<Point>{
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


    private fun getColorFromValue(): Color{
        if (value == 5){    // red-orange
            return Color(0xFFf2a48a)
        }
        else if (value == 10){ // green
            return Color(0xFF8af29d)
        }
        else if (value == 15){  // blue
            return Color(0xFF8ad5f2)
        }
        else if (value == 20){  // purple
            return Color(0xFFdf8af2)
        }
        else if (value == 25){ // orange
            return Color(0xFFF2C38A)
        }
        else if (value == 30){  // yellow
            return Color(0xFFf2ed8a)
        }
        else if (value == 35){  // greenish blue
            return Color(0xFF8AF2D5)
        }
        return Color.Black
    }


}