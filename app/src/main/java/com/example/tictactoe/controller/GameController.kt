package com.example.tictactoe.controller

import com.example.tictactoe.model.GameModel
import com.example.tictactoe.R
import com.example.tictactoe.delegate.MainDelegate

class GameController(
    private val delegate: MainDelegate
) {
    private val model: GameModel = GameModel()

    private var gameActive = true
    private var activePlayer = 0


    fun handleCellClick(tappedImage: Int){
        if (!gameActive) {
            gameReset()
            return
        }
        if(model.gameState[tappedImage] == 2) {
            model.gameState[tappedImage] = activePlayer
            if (activePlayer == 0) {
                delegate.setCell(R.drawable.x, R.color.cell_fill_color_x)
                activePlayer = 1
                delegate.setStatus("O\'s Turn - Tap to play")
            } else {
                delegate.setCell(R.drawable.o, R.color.cell_fill_color_o)
                activePlayer = 0
                delegate.setStatus("X\'s Turn - Tap to play")
            }
        }
        winnerCheck()
        drawCheck()
    }

    private fun winnerCheck() {
        for (winPosition in model.winPositions) {
            if (model.gameState[winPosition[0]] == model.gameState[winPosition[1]] &&
                model.gameState[winPosition[1]] == model.gameState[winPosition[2]] &&
                model.gameState[winPosition[0]] != 2
            ) {
                gameActive = false
                val winnerStr: String = if (model.gameState[winPosition[0]] == 0) {
                    "X has won!! ♛"
                } else {
                    "O has won!! ♛"
                }
                delegate.setStatus(winnerStr)
            }
        }
    }

    private fun drawCheck(){
        val containsTwo = model.gameState.contains(2)
        if(!containsTwo && gameActive) {
            gameActive = false
            delegate.setStatus("Match draw!!")
        }
    }

    fun gameReset(){
        gameActive = true
        activePlayer = 0
        model.gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
        delegate.gameResetUI()
    }
}
