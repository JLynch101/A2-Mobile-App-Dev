package org.wit.finance.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_finance.*
import kotlinx.android.synthetic.main.login_screen.*
import org.jetbrains.anko.*
import org.wit.finance.R
import org.wit.finance.helpers.readImage
import org.wit.finance.helpers.readImageFromPath
import org.wit.finance.helpers.showImagePicker
import org.wit.finance.main.MainApp
import org.wit.finance.models.Location
import org.wit.finance.models.FinanceModel

class FinanceLogin : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)


        login_button.setOnClickListener {
            var status =
                    if (login_username.text.toString().equals("Guest") && login_password.text.toString().equals("guest"))
                "Login successful"
            else "Login Failed, Try again!"
        }
        login_button.setOnClickListener {
            startActivity<FinanceListActivity>()
        }
        register_button.setOnClickListener {
            startActivity<FinanceRegister>()
        }
    }
}



