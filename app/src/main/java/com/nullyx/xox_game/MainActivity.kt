package com.nullyx.xox_game

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.nullyx.xox_game.databinding.ActivityMainBinding
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
    private var player="P1"
    private var xScore:Double=0.0
    private var oScore:Double=0.0
    private var winValue by Delegates.notNull<Boolean>()

    private lateinit var binding:ActivityMainBinding
    private lateinit var buttonArray:Array<Button>
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
         winValue=false
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonArray= arrayOf(binding.b1,binding.b2,binding.b3,binding.b4,binding.b5,binding.b6,binding.b7,binding.b8,binding.b9)
        buttonArray.forEach {
            buttonClickListener(it)
            it.textSize=30f
        }
        binding.xScore.text="X Score: $xScore"
        binding.oScore.text="O Score: $oScore"


    }
    private fun textColorChange(button:Button){
        if(binding.turnText.text=="Turn at O"){
             button.setTextColor(Color.parseColor("#FF0000"))
        }
        else{
             button.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }
    private fun buttonClickListener(button: Button){
        button.setOnClickListener{
            gameButtonClick(button)
        }
        binding.restartButton.setOnClickListener {
            resetGame()
        }
    }
    @SuppressLint("SetTextI18n")
    private fun gameButtonClick(button: Button){
        if(!winValue) {
            if (button.text.isEmpty()) {
                binding.turnText.text = "Turn at X"
                if (player == "P1") {
                    binding.turnText.text = "Turn at O"
                    button.text = "X"
                    player = "P2"

                } else {
                    binding.turnText.text = "Turn at X"
                    button.text = "O"
                    player = "P1"

                }
                textColorChange(button)

                win()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun win(){

        if((binding.b1.text=="X" && binding.b2.text=="X" && binding.b3.text=="X")
            ||(binding.b4.text=="X" && binding.b5.text=="X" && binding.b6.text=="X")
            ||(binding.b7.text=="X" && binding.b8.text=="X" && binding.b9.text=="X")
            ||(binding.b1.text=="X" && binding.b4.text=="X" && binding.b7.text=="X")
            ||(binding.b2.text=="X" && binding.b5.text=="X" && binding.b8.text=="X")
            ||(binding.b3.text=="X" && binding.b6.text=="X" && binding.b9.text=="X")
            ||(binding.b1.text=="X" && binding.b5.text=="X" && binding.b9.text=="X")
            ||(binding.b3.text=="X" && binding.b5.text=="X" && binding.b7.text=="X")){
            gameAlert('X')
            xScore++
            winValue=true

            binding.xScore.text="X Score: $xScore"
        }
         else if((binding.b1.text=="O" && binding.b2.text=="O" && binding.b3.text=="O")
            ||(binding.b4.text=="O" && binding.b5.text=="O" && binding.b6.text=="O")
            ||(binding.b7.text=="O" && binding.b8.text=="O" && binding.b9.text=="O")
            ||(binding.b1.text=="O" && binding.b4.text=="O" && binding.b7.text=="O")
            ||(binding.b2.text=="O" && binding.b5.text=="O" && binding.b8.text=="O")
            ||(binding.b3.text=="O" && binding.b6.text=="O" && binding.b9.text=="O")
            ||(binding.b1.text=="O" && binding.b5.text=="O" && binding.b9.text=="O")
            ||(binding.b3.text=="O" && binding.b5.text=="O" && binding.b7.text=="O")) {
             gameAlert('O')
             winValue=true
             oScore++

            binding.oScore.text="O Score: $oScore"
         }
        else if((binding.b1.text.isNotEmpty())
            &&(binding.b2.text.isNotEmpty())
            &&(binding.b3.text.isNotEmpty())
            &&(binding.b4.text.isNotEmpty())
            &&(binding.b5.text.isNotEmpty())
            &&(binding.b6.text.isNotEmpty())
            &&(binding.b7.text.isNotEmpty())
            &&(binding.b8.text.isNotEmpty())
            &&(binding.b9.text.isNotEmpty())
        ){
             drawAlert()
            winValue=true
            xScore+=0.5
            oScore+=0.5

            binding.xScore.text="X Score: $xScore"
            binding.oScore.text="O Score: $oScore"
        }
    }

    private fun drawAlert(){
        val alert=AlertDialog.Builder(this)
        alert.setTitle("Draw")
            .setMessage("Draw ! Play Again")
            .setPositiveButton("Yes"){
                    _, _ ->Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show()
                restartGame()
            }.setNegativeButton("No"){
                    _,_->
                Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
                winValue=true
                buttonArray.forEach {
                    it.isEnabled=false
                }
            }
        alert.show()
    }
    private fun gameAlert(char: Char){
        val alert=AlertDialog.Builder(this)
        alert.setTitle("$char Wins")
        .setMessage("$char Wins! Play Again")
        .setPositiveButton("Yes"){
                _, _ ->Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show()
            restartGame()
        }.setNegativeButton("No"){
            _,_->
            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
                winValue=true
        }
        alert.show()
    }
     private fun restartGame(){
         buttonArray.forEach {
             it.text=""
         }
         winValue=false
     }

    @SuppressLint("SetTextI18n")
    private fun resetGame(){
        restartGame()
        xScore=0.0
        oScore= 0.0
        winValue=false
        buttonArray.forEach {
            it.isEnabled=true
        }
        Toast.makeText(this, "Reset Game", Toast.LENGTH_SHORT).show()
        binding.xScore.text="X Score: $xScore"
        binding.oScore.text="O Score: $oScore"
    }

}