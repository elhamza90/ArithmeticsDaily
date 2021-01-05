package com.elhamza90.arithmeticsdaily

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

const val OPERATIONS="ops"
const val DIFFICULTY="diff"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onStartClick(view:View) {
        var operations = ""
        val addition = findViewById<CheckBox>(R.id.additionCheckbox).isChecked
        if (addition) {
            operations += "+"
        }
        val substraction = findViewById<CheckBox>(R.id.substractionCheckbox).isChecked
        if (substraction) {
            operations += "-"
        }
        val multiplication = findViewById<CheckBox>(R.id.multiplicationCheckbox).isChecked
        if (multiplication) {
            operations += "x"
        }
        val division = findViewById<CheckBox>(R.id.divisionCheckbox).isChecked
        if (division) {
            operations += "÷"
        }
        if(operations.length == 0) {
            val builder = AlertDialog.Builder(view.context)
            builder.setTitle("Erreur")
            builder.setMessage("Vous devez selectioner au moins une operation")
            builder.show()
            return
        }

        val checkedDifficultyId = findViewById<RadioGroup>(R.id.difficultyRadioGrp).checkedRadioButtonId
        val checkedDifficultyRadio = findViewById<RadioButton>(checkedDifficultyId)
        val difficulty = checkedDifficultyRadio.text

        val intent = Intent(this, ExerciseActivity::class.java).apply {
            putExtra(OPERATIONS, operations)
            putExtra(DIFFICULTY, difficulty)
        }
        startActivity(intent)
    }

}