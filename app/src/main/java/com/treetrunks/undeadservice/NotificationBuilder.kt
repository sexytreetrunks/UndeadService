package com.treetrunks.undeadservice

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class NotificationBuilder(val context: Context) {

    companion object {
        const val CHANNEL_ID = "com.treetrunks.undeadservice"
        const val FOREGROUND_ID = 333
    }

    fun createNotification(): Notification {
        val title = context.getString(R.string.app_name)
        val content = context.getString(R.string.noti_msg)
        val intent = Intent(context, MainActivity::class.java)
        return buildNotification(title, content, intent)
    }

    private fun buildNotification(title: String, content: String, intent:Intent): Notification {
        return NotificationCompat
            .Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.noti_logo)
            .setContentTitle(title)
            .setContentText(content)
            //.setVibrate(Long[1] {0})
            .setContentIntent(getPendingIntent(intent))
            .build()
    }

    private fun getPendingIntent(targetIntent: Intent): PendingIntent {
        return PendingIntent.getActivity(
            context,
            0,
            targetIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}