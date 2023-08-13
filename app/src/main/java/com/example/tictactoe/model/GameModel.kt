package com.example.tictactoe.model

class GameModel {
    // Current state of the game
    // State meanings:  0 - X    1 - O   2 - Null
    var gameState = IntArray(9) { 2 }

    // Winning positions for the game
    val winPositions = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
    )

}