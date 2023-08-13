package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tictactoe.controller.GameController
import com.example.tictactoe.delegate.MainDelegate

class MainActivity : AppCompatActivity(), MainDelegate {

    private lateinit var image :ImageView
    private lateinit var controller: GameController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = GameController(this)
    }

    // Upon a player's click on any cell
    override fun playerTap(view: View) {
        image = view as ImageView
        val tappedImage = image.tag.toString().toInt()
        controller.handleCellClick(tappedImage)
    }

    // Upon a player's click on reset button
    override fun startNewRound(view: View) {
        controller.gameReset()
    }

    // Apply color change and animation when placing 'X' or 'O' in a cell
    override fun setCell(drawableResId: Int, color: Int) {
        image.translationY = -1000f
        val drawable = ContextCompat.getDrawable(this, drawableResId)
        image.setImageDrawable(drawable)
        val backgroundColor = ContextCompat.getColor(this, color)
        image.setBackgroundColor(backgroundColor)
        image.animate().translationYBy(1000f).duration = 100
    }

    // Update the status text bar with the provided message
    override fun setStatus(statusMessage: String) {
        val status = findViewById<TextView>(R.id.status)
        status.text = statusMessage
    }

    // Resetting all UI components to their initial state after a game concludes or when the reset button is pressed
    override fun gameResetUI() {
        val imageViews = arrayOf(
            R.id.imageView0, R.id.imageView1, R.id.imageView2,
            R.id.imageView3, R.id.imageView4, R.id.imageView5,
            R.id.imageView6, R.id.imageView7, R.id.imageView8
        )
        for (imageViewId in imageViews) {
            val imageView = findViewById<ImageView>(imageViewId)
            imageView.setImageResource(0)
            val backgroundColor = ContextCompat.getColor(this, R.color.cell_blank_color)
            imageView.setBackgroundColor(backgroundColor)
        }
        setStatus(getString(R.string.x_s_turn_tap_to_play))
    }
}
