package com.aurapps.reigntask.app

import android.app.Application
import com.aurapps.reigntask.model.AppDatabase

class App : Application() {

    val applicationGraph: ApplicationGraph = DaggerApplicationGraph.create()
    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        appDatabase = AppDatabase.invoke(applicationContext)
    }

}