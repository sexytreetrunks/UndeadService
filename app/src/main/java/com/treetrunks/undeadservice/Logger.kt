package com.treetrunks.undeadservice

import android.util.Log

object Logger {

    private val LOG_TAG = "RASSWARD"

    fun e(message: String) {
        Log.e(LOG_TAG, message)
    }

    fun d(message: String) {
        Log.d(LOG_TAG, message)
    }

    fun i(message: String) {
        Log.i(LOG_TAG, message)
    }

}