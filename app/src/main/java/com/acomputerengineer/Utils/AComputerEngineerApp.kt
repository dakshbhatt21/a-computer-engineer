package com.acomputerengineer.Utils

import android.app.Application

class AComputerEngineerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}