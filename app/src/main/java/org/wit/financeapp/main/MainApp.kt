package org.wit.finance.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.finance.models.FinanceMemStore

class MainApp : Application(), AnkoLogger {

    val finances = FinanceMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Finance started")
    }
}

