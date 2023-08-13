package com.example.tictactoe.delegate

import android.view.View

interface MainDelegate {
    fun setStatus(statusMessage: String)
    fun gameResetUI()
    fun setCell(drawableResId: Int, color: Int)
    fun playerTap(view: View)
    fun startNewRound(view: View)
}