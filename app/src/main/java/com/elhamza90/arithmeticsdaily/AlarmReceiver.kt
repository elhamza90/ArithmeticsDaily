package com.elhamza90.arithmeticsdaily

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

const val NOTIF_CHANNEL_ID = "my_notif_channel_id"

class AlarmReceiver: BroadcastReceiver() {

    private val notificationId = 1234

    override fun onReceive(context: Context, intent: Intent?) {

        sendNotification(context)

        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val defaultRingtone = RingtoneManager.getRingtone(context, uri)

        // Get the device default ringtone
        val ringtone: Ringtone = defaultRingtone

        if (!ringtone.isPlaying){
                ringtone.play()
        }

    }

    private fun sendNotification(ctx: Context) {

        // Create an explicit intent for an Activity in your app
        val intent = Intent(ctx, ExerciseActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }.apply {
            putExtra(OPERATIONS, "+-")
            putExtra(DIFFICULTY, "Facile")
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(ctx, 0, intent, 0)


        val builder = NotificationCompat.Builder(ctx, NOTIF_CHANNEL_ID)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setSmallIcon(R.mipmap.ic_launcher)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)


        with(NotificationManagerCompat.from(ctx)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }
    }


}


