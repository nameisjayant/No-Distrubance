package com.locosub.focus_work.utils

import android.annotation.TargetApi
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat


class BoardCastService : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager = NotificationManagerCompat.from(context!!)

        val notificationId = 1
        notificationManager.cancel(notificationId)
    }

}

class Block_All_Notification : NotificationListenerService() {
    override fun onBind(intent: Intent): IBinder? {
        return super.onBind(intent)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onNotificationPosted(sbn: StatusBarNotification) {

        Log.d("Msg", "Notification arrived ${sbn.packageName},${sbn.id},${sbn.key},${sbn.uid}")

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            cancelNotification(sbn.packageName, sbn.tag, sbn.id)
        } else {
            cancelNotification(sbn.key)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        // Implement what you want here
        Log.d("Msg", "Notification Removed")
        clearNotofication(sbn.uid)
        cancelNotification(sbn.key)
    }

    private fun clearNotofication(notificationId: Int) {
        val ns = NOTIFICATION_SERVICE
        val nMgr = this.getSystemService(ns) as NotificationManager
        nMgr.cancel(notificationId)
    }
}

