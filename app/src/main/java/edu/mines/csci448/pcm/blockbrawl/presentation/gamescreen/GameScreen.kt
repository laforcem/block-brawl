package edu.mines.csci448.pcm.blockbrawl.presentation.gamescreen
import android.util.Log
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.MultiParagraph
import androidx.compose.ui.text.TextLayoutInput
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@OptIn(ExperimentalTextApi::class)
@Composable
fun GameScreen(
    blockList: List<Block>,
    onGameOver: (Int) -> Unit,
)
{
    /*
    // Defining game variables
    */
    val timer: MutableState<Float> = remember {mutableStateOf(60.00f)}   //Set a 45 second timer
    val gameOver: MutableState<Boolean> = remember { mutableStateOf(false) }
    val textMeasurer = rememberTextMeasurer()
    val score = remember { mutableStateOf(0) }
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    val shadowOffsetX: MutableState<Float> = remember { mutableStateOf(0f) }
    val shadowOffsetY: MutableState<Float> = remember { mutableStateOf(0f)}
    val blockBeingDragged: MutableState<Block?> = remember { mutableStateOf(null) }
    val validPlacement: MutableState<Boolean> = remember { mutableStateOf(false)}
    val boardWidth = 7
    val boardHeight = 4
    val boardInit = remember { mutableStateOf(false)}
    val currentBlockListState: MutableState<List<Block>> = remember { mutableStateOf(blockList) }
    val gameBoardState: MutableState<Array<Array<Char>>> = remember { mutableStateOf(Array(boardHeight) {Array(boardWidth) {' '} }) }
    val gameBoardTempState: MutableState<Array<Array<Char>>> = remember { mutableStateOf(Array(boardHeight) {Array(boardWidth) {' '} }) }


    //Increment timer
    LaunchedEffect(key1 = timer.value) {
        if (timer.value <= 0.051f) {
            // The game is over when the timer reaches 0
            timer.value = 0.0f
            gameOver.value = true
            onGameOver(score.value)
        }
        else{
            delay(40L)
            timer.value -= 0.05f
        }
    }

    Column(){


    //Display timer and Score
    Box(modifier = Modifier.fillMaxWidth().weight(0.04f)){
        Row(){
            Text(text = getTimerText(timer.value), modifier = Modifier.weight(0.33f), textAlign = TextAlign.Center)
            Button(onClick = { timer.value = 0.0f } ,modifier = Modifier.weight(0.33f).defaultMinSize(minWidth = 1.dp, minHeight = 1.dp), contentPadding = PaddingValues(0.dp, 3.dp)){
                Text("End Game")
            }
            Text(text = "Score: " + score.value.toString(), modifier = Modifier.weight(0.33f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth().weight(0.96f)
    ){
        //Defining game variables that depend on canvas size
        val canvasWidth = LocalDensity.current.run { maxWidth.toPx()}
        val canvasHeight = LocalDensity.current.run { maxHeight.toPx()}
        val WIDTH = canvasWidth / (boardWidth + 1)
        val boardTopLeft_x = WIDTH/2
        val boardTopLeft_y = (canvasHeight - (WIDTH)*boardHeight + WIDTH)/2

        if (!boardInit.value){
            currentBlockListState.value.forEach { block ->
                block.setPosition(canvasWidth, boardWidth, boardTopLeft_y + (WIDTH)*(boardHeight) - 6)
            }
            boardInit.value = true
        }

        /*
        // Canvas for displaying game
         */
        Canvas(modifier = Modifier.fillMaxSize().pointerInput(Unit) {
            /*
            // Code for dragging gestures
             */
            detectDragGestures (
                // Called when the player taps anywhere on the canvas.
                onDragStart = {
                    //Find tapped block
                    if (!gameOver.value){
                        blockBeingDragged.value = getTappedBlock(it, WIDTH, currentBlockListState.value);
                        if (blockBeingDragged.value != null){
                            if (blockBeingDragged.value!!.isPlaced){
                                gameBoardState.value = erasePlacement(blockBeingDragged.value!!, offsetX.value, offsetY.value, WIDTH, boardTopLeft_x, boardTopLeft_y, boardWidth, boardHeight, gameBoardState.value)
                            }
                        }
                    }
                },
                // Called when the player lets go after dragging
                onDragEnd = {
                    if (!gameOver.value){
                        if (validPlacement.value && blockBeingDragged.value != null){
                            // If there is a valid spot for the block, change the block's position to that spot.
                            blockBeingDragged.value!!.x_pos = shadowOffsetX.value
                            blockBeingDragged.value!!.y_pos = shadowOffsetY.value
                            gameBoardState.value = gameBoardTempState.value
                            blockBeingDragged.value!!.isPlaced = true
                            validPlacement.value = false
                        }
                        else if (blockBeingDragged.value != null){
                            //If there is no valid spot, put the block back where it started
                            blockBeingDragged.value!!.x_pos = blockBeingDragged.value!!.init_x_pos
                            blockBeingDragged.value!!.y_pos = blockBeingDragged.value!!.init_y_pos
                            blockBeingDragged.value!!.isPlaced = false
                            validPlacement.value = false
                        }
                        score.value = calculateScore(currentBlockListState.value)
                        //Reset variables
                        blockBeingDragged.value = null
                        offsetX.value = 0f;
                        offsetY.value = 0f;
                    }
                })
            // Called everytime the position of the block changes while it is being dragged
            { change, dragAmount ->
                if (blockBeingDragged.value != null){
                    change.consume()
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                        val data: Pair<Pair<Float, Float>, Array<Array<Char>>>? = isPlacementValid(blockBeingDragged.value!!, offsetX.value, offsetY.value, WIDTH, boardTopLeft_x, boardTopLeft_y, boardWidth, boardHeight, gameBoardState.value)
                        if (data != null){
                            shadowOffsetX.value = data.first.first
                            shadowOffsetY.value = data.first.second
                            gameBoardTempState.value = data.second
                            validPlacement.value = true
                        }
                        else{
                            //Get rid of shadow after going out of range
                            if (abs(shadowOffsetX.value - (offsetX.value + blockBeingDragged.value!!.x_pos)) > WIDTH*2
                                || abs(shadowOffsetY.value - (offsetY.value + blockBeingDragged.value!!.y_pos)) > WIDTH*2 ){
                                validPlacement.value = false
                            }
                        }
                }
            }
        })
        {
            /*
            // Display the entire game.
            */


            //Draw game board outline
            drawRect(
                color = Color.DarkGray,
                size = Size(width = (WIDTH)*boardWidth + 12, height = (WIDTH)*(boardHeight) + 12),
                topLeft = Offset(boardTopLeft_x - 6, boardTopLeft_y - 6),
            )
            //Draw game board
            drawRect(
                color = Color(0xFFdfdfdf),
                size = Size(width = (WIDTH)*boardWidth, height = (WIDTH)*(boardHeight)),
                topLeft = Offset(boardTopLeft_x, boardTopLeft_y),
            )


            if (!gameOver.value){
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
                                size = Size(width = WIDTH + 2, height = WIDTH + 2),
                                topLeft = Offset(shadowOffsetX.value + square.x*WIDTH - 1, shadowOffsetY.value + square.y*WIDTH - 1),
                            )
                        }
                    }
                    //Draw the block being dragged on top of everything else.
                    drawBlock(blockBeingDragged.value!!, offsetX.value, offsetY.value, WIDTH);
                }
            }
            else{
                drawText(textMeasurer = textMeasurer,
                    text = "Game Over!",
                    style = TextStyle(color = Color(0xFF286751), fontSize = 50.sp, fontWeight = FontWeight.Black, textAlign = TextAlign.Center,),
                    softWrap = false,
                    size = Size(width = (WIDTH)*boardWidth, height = (WIDTH)*(boardHeight)),
                    topLeft = Offset(boardTopLeft_x, boardTopLeft_y + (WIDTH)*(boardHeight)/3.2f),
                )
            }

        }
    }
    }
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

    // Determine if the current spot being hovered over is a valid spot. Return the coordinates of that spot, if it exists. Also return the new game board state.
fun isPlacementValid(block: Block, offsetX: Float, offsetY: Float, width: Float, gameBoard_x: Float, gameBoard_y: Float, boardWidth: Int, boardHeight: Int, gameBoardState: Array<Array<Char>>): Pair<Pair<Float, Float>, Array<Array<Char>>>?{
    var block_x = block.x_pos + offsetX
    var block_y = block.y_pos + offsetY

    var gameBoard: Array<Array<Char>> = Array(boardHeight) {Array(boardWidth) {' '} }

    gameBoard.forEachIndexed {i, row ->
        row.forEachIndexed {j, char ->
            gameBoard[i][j] = gameBoardState[i][j]
        }
    }

    // Check if the block's center is in the range of the gameboard
    //if (block_x >= gameBoard_x && block_y >= gameBoard_y && block_x < gameBoard_x + boardWidth*width && block_y < gameBoard_y + boardHeight*width){
        block_x -= gameBoard_x - width/2
        block_y -= gameBoard_y - width/2
        val nearest_x_coord = (block_x / width).toInt()
        val nearest_y_coord = (block_y / width).toInt()
        val nearest_x = nearest_x_coord * width
        val nearest_y = nearest_y_coord * width

        //Check if the block can actually fit in that spot
        block.squares.forEach { square ->
            val square_x_coord = nearest_x_coord + square.x
            val square_y_coord = nearest_y_coord + square.y
            //Check if this particular square is in range of the board
            if (square_x_coord >= 0 && square_x_coord < boardWidth && square_y_coord >= 0 && square_y_coord < boardHeight) {
                //Check if the spot is already taken
                if (gameBoard[square_y_coord][square_x_coord] != ' '){
                    return null
                }
                gameBoard[square_y_coord][square_x_coord] = 'X'
            }
            else{
                return null
            }
        }
        return Pair(Pair(gameBoard_x + nearest_x, gameBoard_y + nearest_y), gameBoard)
    //}
    //return null;
}

//When a block that has already been placed on the board is dragged, erase its position from the game board.
fun erasePlacement(block: Block, offsetX: Float, offsetY: Float, width: Float, gameBoard_x: Float, gameBoard_y: Float, boardWidth: Int, boardHeight: Int, gameBoardState: Array<Array<Char>>): Array<Array<Char>>{
    var block_x = block.x_pos + offsetX
    var block_y = block.y_pos + offsetY

    var gameBoard: Array<Array<Char>> = Array(boardHeight) {Array(boardWidth) {' '} }

    gameBoard.forEachIndexed {i, row ->
        row.forEachIndexed {j, char ->
            gameBoard[i][j] = gameBoardState[i][j]
        }
    }

    // Check if the block's center is in the range of the gameboard
    if (block_x >= gameBoard_x && block_y >= gameBoard_y && block_x < gameBoard_x + boardWidth*width && block_y < gameBoard_y + boardHeight*width){
        block_x -= gameBoard_x - width/2
        block_y -= gameBoard_y - width/2
        val nearest_x_coord = (block_x / width).toInt()
        val nearest_y_coord = (block_y / width).toInt()

        //Check if the block can actually fit in that spot
        block.squares.forEach { square ->
            val square_x_coord = nearest_x_coord + square.x
            val square_y_coord = nearest_y_coord + square.y

            gameBoard[square_y_coord][square_x_coord] = ' '
        }
    }

    return gameBoard;
}



fun calculateScore(blocks: List<Block>): Int{
    var score = 0;
    blocks.forEach {block ->
        if (block.isPlaced){
            score += block.value
        }
    }
    return score
}

fun getTimerText(timer: Float): String{
    var text = ((timer*100).roundToInt().toFloat()/100).toString()
    if ((timer > 10 && text.length < 5) || (timer < 10 && text.length < 4)){
        text += "0"
    }
    return text
}


fun DrawScope.drawBlock(block: Block, offsetX: Float, offsetY: Float, width: Float){
    block.squares.forEach { square ->
        //Draw the outline
        drawRect(
            color = Color.DarkGray,
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
        // Fill sides
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
        //Fill corners
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
