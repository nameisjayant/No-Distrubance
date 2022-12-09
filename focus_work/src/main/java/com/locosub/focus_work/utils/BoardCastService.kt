package com.locosub.focus_work.utils

import android.R
import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.NotificationCompat


class BoardCastService : Service() {

    companion object {
        private const val CHANNEL_ID = "ForegroundServiceChannel"
        const val COUNTDOWN_BR = "com.locosub.focus_work"
    }

    val intent = Intent(COUNTDOWN_BR)
    private var cdt: CountDownTimer? = null

    override fun onCreate() {
        super.onCreate()
        cdt = object : CountDownTimer(50000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                intent.putExtra("countdown", millisUntilFinished)
                intent.putExtra("countdownTimerRunning", true)
                intent.putExtra("countdownTimerFinished", false)
                sendBroadcast(intent)
            }

            override fun onFinish() {
                intent.putExtra("countdownTimerFinished", true)
                sendBroadcast(intent)
                stopForeground(true)
                stopSelf()
            }

        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        cdt!!.cancel()
        sendBroadcast(intent)
        super.onDestroy()
    }


    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        /* Notification */
        val input = intent.getStringExtra("inputExtra")
//        createNotificationChannel()
//        val notificationIntent = Intent(this, BroadcastReceiver::class.java)
//        val pendingIntent = PendingIntent.getActivity(
//            this,
//            0, notificationIntent, 0
//        )
//        /* NotificationBuilder */
//        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setContentTitle("Foreground Service")
//            .setContentText(input)
//            .setSmallIcon(R.drawable.sym_def_app_icon)
//            .setContentIntent(pendingIntent)
//            .build()
//        startForeground(1, notification)
        return START_NOT_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }

}

