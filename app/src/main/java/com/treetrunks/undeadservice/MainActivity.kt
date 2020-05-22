package com.treetrunks.undeadservice

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startMainService()
    }

    override fun onResume() {
        super.onResume()


    }

    private fun startMainService() {
        val intentServiceChecker = Intent(this, MainService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intentServiceChecker)
        } else {
            startService(intentServiceChecker)
        }
    }
}
