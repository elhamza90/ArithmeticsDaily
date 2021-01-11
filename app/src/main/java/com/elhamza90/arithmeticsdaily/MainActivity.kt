package com.elhamza90.arithmeticsdaily

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

const val OPERATIONS="ops"
const val DIFFICULTY="diff"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

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

    fun onSetAlarmClick(view:View) {
        val intent = Intent(this, AlarmActivity::class.java)
        startActivity(intent)
    }


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channel_name"
            val descriptionText = "My notification to wake up"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NOTIF_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}