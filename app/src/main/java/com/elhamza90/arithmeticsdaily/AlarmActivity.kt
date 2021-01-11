package com.elhamza90.arithmeticsdaily

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class AlarmActivity : AppCompatActivity() {
    var alarmTime : Date = Date()
    var alarmTimeStr : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onPickTimeClick(view: View) {
        val cal = Calendar.getInstance()
        val selectedTimeTv = findViewById<TextView>(R.id.selectedTimeTv)
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            alarmTime = cal.time
            alarmTimeStr = SimpleDateFormat("HH:mm").format(cal.time)
            selectedTimeTv.text = alarmTimeStr


        }
        TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onUseAlarmClick(view: View) {
        var alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(applicationContext, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 123, intent, 0)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTime.time, pendingIntent)
        Toast.makeText(applicationContext, alarmTimeStr, Toast.LENGTH_SHORT).show()
    }



}