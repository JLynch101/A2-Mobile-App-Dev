package org.wit.financeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class FinanceActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        info("Finance Activity started..")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finance)
    }
}