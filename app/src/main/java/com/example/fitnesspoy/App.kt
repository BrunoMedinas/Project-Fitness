package com.example.fitnesspoy

import android.app.Application
import com.example.fitnesspoy.model.AppDateBase

class App : Application() {

    lateinit var db : AppDateBase

    override fun onCreate() {
        super.onCreate()
        db = AppDateBase.getDataBase(this)
    }
}