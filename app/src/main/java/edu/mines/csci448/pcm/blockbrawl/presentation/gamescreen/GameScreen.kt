package edu.mines.csci448.pcm.blockbrawl.presentation.gamescreen
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.PreviewBlockBrawlViewModel

@Composable
fun GameScreen(
    blockBrawlViewModel: IBlockBrawlViewModel,
    onPauseClicked: () -> Unit
)
{
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    val draggingBlock = remember { mutableStateOf(false) }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ){
        val canvasWidth = LocalDensity.current.run { maxWidth.toPx()}
        val canvasHeight = LocalDensity.current.run { maxHeight.toPx()}
        val WIDTH = canvasWidth / 11

        //TODO: set up a list of blocks in the ViewModel. This is just a temporary test block
        val block = Block(
            100f,
            300f,
            arrayOf(arrayOf(true)),
            Color(0xFF6a4db3),
            canvasWidth,
            canvasHeight,
            11
        )

        //Game content goes here
            Canvas(modifier = Modifier.fillMaxSize().pointerInput(Unit) {
                detectDragGestures (
                    onDragStart = {
                        //TODO: use the getTappedBlock function to determine what block (if any) was tapped
                        if (it.x > block.x_pos
                            && it.x < block.x_pos + WIDTH
                            && it.y > block.y_pos
                            && it.y < block.y_pos + WIDTH){
                                draggingBlock.value = true
                            }
                    },
                    onDragEnd = {
                        draggingBlock.value = false
                        block.x_pos = block.x_pos + offsetX.value
                        block.y_pos = block.y_pos + offsetY.value
                        offsetX.value = 0f;
                        offsetY.value = 0f;
                    })
                { change, dragAmount ->
                    if (draggingBlock.value){
                        change.consume()
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                    }
                }
            })
            {

                drawBlock(block, offsetX.value, offsetY.value);
            }
    }
}

fun getTappedBlock(offset: Offset): Block? {
    //TODO: set up a list of all blocks that have not been placed on
    //      the board. Then figure out which one was pressed and return
    //      that block so it can be dragged.
    return null
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview(){
    GameScreen(blockBrawlViewModel = PreviewBlockBrawlViewModel()) {}
}
