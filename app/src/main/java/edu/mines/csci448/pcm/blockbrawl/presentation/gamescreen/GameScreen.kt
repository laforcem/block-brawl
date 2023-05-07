package edu.mines.csci448.pcm.blockbrawl.presentation.gamescreen
import android.util.Log
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel

@Composable
fun GameScreen(
    blockBrawlViewModel: IBlockBrawlViewModel,
    onPauseClicked: () -> Unit
)
{
    val LOG_TAG = "448.GameScreen"
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    val shadowOffsetX: MutableState<Float> = remember { mutableStateOf(0f) }
    val shadowOffsetY: MutableState<Float> = remember { mutableStateOf(0f)}
    val blockBeingDragged: MutableState<Block?> = remember { mutableStateOf(null) }
    val validPlacement: MutableState<Boolean> = remember { mutableStateOf(false)}
    val boardWidth = 8
    val boardHeight = 5

        // This is just a temporary test block
        val block1 = Block(
            100f,
            50f,
            arrayOf(
                arrayOf('C'),
                arrayOf('X')
            ),
            Color(0xFF6a4db3),
            false
        )

        val block2 = Block(
            230f,
            50f,
            arrayOf(
                arrayOf('X', 'C'),
                arrayOf(' ', 'X')
            ),
            Color(0xFF118c23),
            false
        )

        val block3 = Block(
            470f,
            50f,
            arrayOf(
                arrayOf('X', 'X', 'X'),
                arrayOf('X', 'C', 'X'),
                arrayOf('X', 'X', 'X')
            ),
            Color(0xFF1d7dde),
            false
        )



    //TODO: set up a list of blocks in the ViewModel.
    // for now the list is defined here.
    val currentBlockListState: MutableState<List<Block>> = remember { mutableStateOf(listOf(block1, block2, block3)) }
    val gameBoardState: MutableState<Array<Array<Char>>> = remember { mutableStateOf(Array(boardHeight) {Array(boardWidth) {' '} }) }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ){
        val canvasWidth = LocalDensity.current.run { maxWidth.toPx()}
        val canvasHeight = LocalDensity.current.run { maxHeight.toPx()}
        val WIDTH = canvasWidth / (boardWidth + 1)
        val boardTopLeft_x = WIDTH/2
        val boardTopLeft_y = canvasHeight - (WIDTH)*boardHeight - WIDTH/2

        //Game content goes here
        Canvas(modifier = Modifier.fillMaxSize().pointerInput(Unit) {
            detectDragGestures (
                onDragStart = {   //When the player taps anywhere on the canvas.
                    //Find tapped block
                    blockBeingDragged.value = getTappedBlock(it, WIDTH, currentBlockListState.value);
                },
                onDragEnd = {   //When the player lets go of the block.
                    if (validPlacement.value && blockBeingDragged.value != null){
                        // If there is a valid spot for the block, change the block's position to that spot.
                        blockBeingDragged.value!!.x_pos = shadowOffsetX.value
                        blockBeingDragged.value!!.y_pos = shadowOffsetY.value
                        blockBeingDragged.value!!.isPlaced = true
                        validPlacement.value = false
                    }
                    else{
                        //If there is no valid spot, put the block back where it started
                        blockBeingDragged.value!!.x_pos = blockBeingDragged.value!!.init_x_pos
                        blockBeingDragged.value!!.y_pos = blockBeingDragged.value!!.init_y_pos
                        blockBeingDragged.value!!.isPlaced = false
                        validPlacement.value = false
                    }
                    //Reset variables
                    blockBeingDragged.value = null
                    offsetX.value = 0f;
                    offsetY.value = 0f;
                })
            { change, dragAmount ->     //This gets called everytime the position of the block changes while it is being dragged
                if (blockBeingDragged.value != null){
                    change.consume()
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                    val point: Pair<Float, Float>? = isPlacementValid(blockBeingDragged.value!!, offsetX.value, offsetY.value, WIDTH, boardTopLeft_x, boardTopLeft_y, boardWidth, boardHeight)
                    if (point != null){
                        shadowOffsetX.value = point.first
                        shadowOffsetY.value = point.second
                        validPlacement.value = true
                    }
                }
            }
        })
        {
            /*
            // Display the entire game.
            */

            //Draw game board
            drawRect(
                color = Color(0xFFdfdfdf),
                size = Size(width = (WIDTH)*boardWidth, height = (WIDTH)*(boardHeight)),
                topLeft = Offset(boardTopLeft_x, boardTopLeft_y),
            )


            //Draw all blocks
            currentBlockListState.value.forEach { block ->
                if (block != blockBeingDragged.value){  //Don't draw the block being dragged twice.
                    drawBlock(block, 0.0f, 0.0f, WIDTH);
                }
            }

            if (blockBeingDragged.value != null){
                if (validPlacement.value){
                    blockBeingDragged.value!!.squares.forEach {square ->
                        //Draw the shadow of the valid placement for the dragged block on the game board
                        drawRect(
                            color = Color.Gray,
                            size = Size(width = WIDTH, height = WIDTH),
                            topLeft = Offset(shadowOffsetX.value + square.x*WIDTH, shadowOffsetY.value + square.y*WIDTH),
                        )
                    }
                }
                //Draw the block being dragged on top of everything else.
                drawBlock(blockBeingDragged.value!!, offsetX.value, offsetY.value, WIDTH);
            }
        }

    }
}

// Determine if the current spot being hovered over is a valid spot.
fun isPlacementValid(block: Block, offsetX: Float, offsetY: Float, width: Float, gameBoard_x: Float, gameBoard_y: Float, boardWidth: Int, boardHeight: Int): Pair<Float, Float>?{
    var block_x = block.x_pos + offsetX
    var block_y = block.y_pos + offsetY

    // Check if the block is in the range of the gameboard
    if (block_x >= gameBoard_x && block_y >= gameBoard_y && block_x < gameBoard_x + boardWidth*width && block_y < gameBoard_y + boardHeight*width){
        block_x -= gameBoard_x - width/2
        block_y -= gameBoard_y - width/2
        val nearest_x_coord = (block_x / width).toInt()
        val nearest_y_coord = (block_y / width).toInt()
        val nearest_x = nearest_x_coord * width
        val nearest_y = nearest_y_coord * width
        block.squares.forEach { square ->
            val max_y = block.shape.size
            val max_x = block.shape[0].size
            val shape_x = square.x + block.center_x
            val shape_y = square.y + block.center_y



        }
        return Pair(gameBoard_x + nearest_x, gameBoard_y + nearest_y);
    }


    return null;
}

fun getTappedBlock(offset: Offset, width: Float, blockList: List<Block>): Block? {
    // Loop through each block and determine if any were tapped.
    blockList.forEach { block ->
        block.squares.forEach { square ->
            if (offset.x - width/2 > block.x_pos - width*0.85 + square.x*width
                && offset.x - width/2 < block.x_pos + width*0.85 + square.x*width
                && offset.y - width/2 > block.y_pos - width*0.85 + square.y*width
                && offset.y - width/2 < block.y_pos + width*0.85 + square.y*width){
                return block
            }
        }
    }
    return null
}

fun DrawScope.drawBlock(block: Block, offsetX: Float, offsetY: Float, width: Float){
    block.squares.forEach { square ->
        //Draw the outline

        drawRect(
            color = Color.Black,
            size = Size(width = width + 2, height = width + 2),
            topLeft = Offset(block.x_pos + offsetX + square.x*width - 1, block.y_pos + offsetY + square.y*width - 1),
        )
        //Draw the block

        drawRect(
            color = block.color,
            size = Size(width = width - 12, height = width - 12),
            topLeft = Offset(block.x_pos + offsetX + square.x*width + 6, block.y_pos + offsetY + square.y*width + 6),
        )




        //
        // Fill in spots where outlines shouldn't be
        //
        val max_y = block.shape.size
        val max_x = block.shape[0].size
        val shape_x = square.x + block.center_x
        val shape_y = square.y + block.center_y

        //
        // Check sides
        //
        // top

        if (shape_y > 0){
            if (block.shape[shape_y - 1][shape_x] != ' '){
                drawRect(
                    color = block.color,
                    size = Size(width = width - 12, height = width/2),
                    topLeft = Offset(block.x_pos + offsetX + square.x*width + 6, block.y_pos + offsetY + square.y*width - width/4),
                )
            }
        }
        // left
        if (shape_x > 0){
            if (block.shape[shape_y][shape_x - 1] != ' '){
                drawRect(
                    color = block.color,
                    size = Size(width = width/2, height = width - 12),
                    topLeft = Offset(block.x_pos + offsetX + square.x*width + 6 - width/4, block.y_pos + offsetY + square.y*width + 6),
                )
            }
        }
        // right
        if (shape_x < max_x - 1){
            if (block.shape[shape_y][shape_x + 1] != ' '){
                drawRect(
                    color = block.color,
                    size = Size(width = width/2, height = width - 12),
                    topLeft = Offset(block.x_pos + offsetX + square.x*width + 6 + width/4 + width/2, block.y_pos + offsetY + square.y*width + 6),
                )
            }
        }
        // bottom
        if (shape_y < max_y - 1){
            if (block.shape[shape_y + 1][shape_x] != ' '){
                drawRect(
                    color = block.color,
                    size = Size(width = width - 12, height = width/2),
                    topLeft = Offset(block.x_pos + offsetX + square.x*width + 6, block.y_pos + offsetY + square.y*width + width/4 + width/2),
                )
            }
        }


        //
        //Check corners
        //

        //bottom right
        if (shape_y < max_y - 1 && shape_x < max_x - 1) {
            if (block.shape[shape_y + 1][shape_x + 1] != ' ' && block.shape[shape_y + 1][shape_x] != ' ' &&block.shape[shape_y][shape_x + 1] != ' '){
                drawRect(
                    color = block.color,
                    size = Size(width = width, height = width),
                    topLeft = Offset(block.x_pos + offsetX + square.x*width + width/2, block.y_pos + offsetY + square.y*width + width/2),
                )
            }
        }
        //bottom left
        if (shape_y < max_y - 1 && shape_x > 0) {
            if (block.shape[shape_y + 1][shape_x - 1] != ' ' && block.shape[shape_y + 1][shape_x] != ' ' &&block.shape[shape_y][shape_x - 1] != ' '){
                drawRect(
                    color = block.color,
                    size = Size(width = width, height = width),
                    topLeft = Offset(block.x_pos + offsetX + square.x*width + width/2 - width, block.y_pos + offsetY + square.y*width + width/2),
                )
            }
        }
        //top left
        if (shape_y > 0 && shape_x > 0) {
            if (block.shape[shape_y - 1][shape_x - 1] != ' ' && block.shape[shape_y - 1][shape_x] != ' ' &&block.shape[shape_y][shape_x - 1] != ' '){
                drawRect(
                    color = block.color,
                    size = Size(width = width, height = width),
                    topLeft = Offset(block.x_pos + offsetX + square.x*width + width/2 - width, block.y_pos + offsetY + square.y*width + width/2 - width),
                )
            }
        }
        //top right
        if (shape_y > 0 && shape_x < max_x - 1) {
            if (block.shape[shape_y - 1][shape_x + 1] != ' ' && block.shape[shape_y - 1][shape_x] != ' ' &&block.shape[shape_y][shape_x + 1] != ' '){
                drawRect(
                    color = block.color,
                    size = Size(width = width, height = width),
                    topLeft = Offset(block.x_pos + offsetX + square.x*width + width/2, block.y_pos + offsetY + square.y*width + width/2 - width),
                )
            }
        }
    }
}