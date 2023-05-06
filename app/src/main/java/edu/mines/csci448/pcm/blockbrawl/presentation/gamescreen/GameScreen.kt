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
    val blockBeingDragged: MutableState<Block?> = remember { mutableStateOf(null) }
    val boardSize = 9;

        // This is just a temporary test block
        val block1 = Block(
            100f,
            300f,
            listOf(Point(0, 0), Point(0, 1)),
            Color(0xFF6a4db3),
            false
        )
        val block2 = Block(
            230f,
            300f,
            listOf(Point(0, 0), Point(1, 0), Point(1, 1)),
            Color(0xFF118c23),
            false
        )
    val block3 = Block(
        470f,
        407f,
        listOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(2, -1)),
        Color(0xFF1d7dde),
        false
    )

    //TODO: set up a list of blocks in the ViewModel.
    // for now the list is defined here.
    val currentBlockListState: MutableState<List<Block>> = remember { mutableStateOf(listOf(block1, block2, block3)) }
    val gameBoardState: MutableState<Array<Array<Block?>>> = remember { mutableStateOf(Array(boardSize) {Array(boardSize) {null} }) }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ){
        val canvasWidth = LocalDensity.current.run { maxWidth.toPx()}
        val canvasHeight = LocalDensity.current.run { maxHeight.toPx()}
        val WIDTH = canvasWidth / (boardSize + 1)

        //Game content goes here
        Canvas(modifier = Modifier.fillMaxSize().pointerInput(Unit) {
            detectDragGestures (
                onDragStart = {
                    Log.d(LOG_TAG, "tap location: (" + (it.x - WIDTH/2) + ", " + (it.y - WIDTH/2) + ")")
                    //Find tapped block
                    blockBeingDragged.value = getTappedBlock(it, WIDTH, currentBlockListState.value);
                },
                onDragEnd = {
                    findBlockPlacement()
                    blockBeingDragged.value = null
                    offsetX.value = 0f;
                    offsetY.value = 0f;
                })
            { change, dragAmount ->
                if (blockBeingDragged.value != null){
                    change.consume()
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                }
            }
        })
        {
            /*
            // Display the entire game.
            */


            //Draw all blocks
            currentBlockListState.value.forEach { block ->
                if (block != blockBeingDragged.value){  //Don't draw the block being dragged twice.
                    drawBlock(block, 0.0f, 0.0f, WIDTH);
                }
            }


            //Draw the block being dragged on top of everything else.
            if (blockBeingDragged.value != null){
                drawBlock(blockBeingDragged.value!!, offsetX.value, offsetY.value, WIDTH);
            }
        }

    }
}

fun findBlockPlacement(){

}

fun getTappedBlock(offset: Offset, width: Float, blockList: List<Block>): Block? {
    // Loop through each block and determine if any were tapped.
    blockList.forEach { block ->
        block.squares.forEach { square ->
            if (!block.isPlaced
                && offset.x - width/2 > block.x_pos - width*0.85 + square.x*width
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
        drawRect(
            color = block.color,
            size = Size(width = width + 2, height = width + 2),
            topLeft = Offset(block.x_pos + offsetX + square.x*width - 1, block.y_pos + offsetY + square.y*width - 1),
        )
    }

}