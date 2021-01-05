package com.elhamza90.arithmeticsdaily

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.concurrent.TimeUnit

class ExerciseActivity : AppCompatActivity() {
    var allowedOperations: String? = ""
    var difficulty: String? = ""
    var answer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        // Get Intent DATA
        allowedOperations = intent.getStringExtra(OPERATIONS)
        difficulty = intent.getStringExtra(DIFFICULTY)

        generateOperation()
    }

    fun generateOperation() {
        var nbr1: Int = 0
        var nbr2: Int = 0
        val randomOp = allowedOperations?.get((0..((allowedOperations?.length)?.minus(1) ?:0 )).random())
        //TimeUnit.SECONDS.sleep(1L)
        findViewById<TextView>(R.id.resultText).setTextColor(Color.parseColor("#FFFFFF"))
        when (randomOp) {
            '+' -> {
                nbr1 = when(difficulty) {
                    "Facile" -> (10..99).random()
                    "Medium" -> (50..300).random()
                    "Difficile" -> (101..999).random()
                    else -> 0
                }
                nbr2 = when(difficulty) {
                    "Facile" -> (10..99).random()
                    "Medium" -> (50..300).random()
                    "Difficile" -> (101..999).random()
                    else -> 0
                }
                answer = nbr1 + nbr2
            }
            '-' -> {
                nbr1 = when(difficulty) {
                    "Facile" -> (10..99).random()
                    "Medium" -> (50..300).random()
                    "Difficile" -> (101..999).random()
                    else -> 0
                }
                nbr2 = when(difficulty) {
                    "Facile" -> (10..nbr1).random()
                    "Medium" -> (50..nbr1).random()
                    "Difficile" -> (101..nbr1).random()
                    else -> 0
                }
                answer = nbr1 - nbr2
            }
            'x' -> {
                nbr1 = when(difficulty) {
                    "Facile" -> (2..10).random()
                    "Medium" -> (11..99).random()
                    "Difficile" -> (99..999).random()
                    else -> 0
                }
                nbr2 = when(difficulty) {
                    "Facile" -> (2..10).random()
                    "Medium" -> (2..10).random()
                    "Difficile" -> (2..10).random()
                    else -> 0
                }
                answer = nbr1 * nbr2
            }
            '÷' -> {
                nbr1 = when(difficulty) {
                    "Facile" -> (2..10).random()
                    "Medium" -> (11..99).random()
                    "Difficile" -> (99..999).random()
                    else -> 0
                }
                nbr2 = when(difficulty) {
                    "Facile" -> (2..nbr1).random()
                    "Medium" -> (2..nbr1).random()
                    "Difficile" -> (2..nbr1).random()
                    else -> 0
                }
                answer = nbr1 / nbr2
            }
        }
        findViewById<TextView>(R.id.operationText).apply{
            text = "$nbr1 $randomOp $nbr2 ="
        }
        val resultTextView = findViewById<TextView>(R.id.resultText)
        resultTextView.text = ""
    }

    fun onNumberClick(view: View) {
        view as Button
        val resultTextView = findViewById<TextView>(R.id.resultText)
        if (view.text != "X") {
            val prevVal = resultTextView.text
            val btnVal = view.text
            resultTextView.text = "$prevVal$btnVal"
        } else {
            resultTextView.text = ""
        }
        val res = (resultTextView.text as String).toIntOrNull()
        if (res == answer) {
            resultTextView.setTextColor(Color.parseColor("#0000EE"))
            generateOperation()
        }
    }
}