package org.wit.finance.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.finance.models.FinanceJSONStore
import org.wit.finance.models.FinanceMemStore
import org.wit.finance.models.FinanceStore

class MainApp : Application(), AnkoLogger {

    lateinit var finances: FinanceStore

    override fun onCreate() {
        super.onCreate()
        finances = FinanceJSONStore(applicationContext)
        info("Finance started")
    }
}

