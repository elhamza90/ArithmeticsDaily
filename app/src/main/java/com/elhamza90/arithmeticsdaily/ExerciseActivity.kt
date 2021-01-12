package com.elhamza90.arithmeticsdaily

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule
const val TAG = "exercise_act"

class ExerciseActivity : AppCompatActivity() {
    var nbrOperations: Int = 2
    var allowedOperations: String? = ""
    var difficulty: String? = ""
    var answer: Int = 0
    var nbrCorrect: Int = 0
    var defaultResultTvColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        // Get Intent DATA
        allowedOperations = intent.getStringExtra(OPERATIONS)
        difficulty = intent.getStringExtra(DIFFICULTY)
        nbrOperations = intent.getIntExtra(NBR_OPERATIONS, 1)
        val nbrOpsTv = findViewById<TextView>(R.id.nbrOpsTv)
        nbrOpsTv.text = "/$nbrOperations"

        generateOperation()
    }

    fun generateOperation() {

        if (nbrOperations == 0) {
                    this.finish()
        }

        var nbr1: Int = 0
        var nbr2: Int = 0
        val randomOp = allowedOperations?.get((0..((allowedOperations?.length)?.minus(1) ?:0 )).random())

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
        val nbrCorrectTv = findViewById<TextView>(R.id.nbrCorrectTv)
        val res_str: String
        if (view.text != "X") {
            val prevVal = resultTextView.text
            val btnVal = view.text
            res_str = "$prevVal$btnVal"

        } else {
            res_str = ""
        }
        val res = res_str.toIntOrNull()
        GlobalScope.launch ( Dispatchers.Main ) {

            resultTextView.text = res_str

            if (res == answer) {
                nbrCorrect++
                nbrCorrectTv.text = nbrCorrect.toString()
                if (nbrCorrect == 1) {
                    nbrCorrectTv.setTextColor(Color.parseColor("#FFC107"))
                    defaultResultTvColor = resultTextView.textColors.defaultColor
                }
                resultTextView.setTextColor(Color.parseColor("#FFC107"))

                nbrOperations--;

                delay(1000L)
                resultTextView.setTextColor(defaultResultTvColor)

                generateOperation()
            }
        }

    }




}