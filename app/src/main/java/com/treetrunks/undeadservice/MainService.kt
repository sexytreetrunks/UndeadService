package com.treetrunks.undeadservice

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*

class MainService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Logger.d("${javaClass.simpleName}_call onStartCommand")

        val notification = NotificationBuilder(this).createNotification()

        startForeground(NotificationBuilder.FOREGROUND_ID, notification)

        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()

        Logger.d("${javaClass.simpleName}_call onDestroy")

        setRestartAlarm()

    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)

        Logger.d("${javaClass.simpleName}_call onTaskRemoved")

        setRestartAlarm()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun setRestartAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            getTriggerMillis(5),
            getPendingIntent(MainService::class.java)
        )
    }

    private fun getTriggerMillis(sec: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, sec)

        return calendar.timeInMillis
    }

    private fun <T> getPendingIntent(cls: Class<T>): PendingIntent {
        return PendingIntent.getService(
            this,
            0,
            Intent(this, cls),
            PendingIntent.FLAG_UPDATE_CURRENT)
    }
}