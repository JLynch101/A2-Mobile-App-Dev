package org.wit.finance.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_finance.*
import kotlinx.android.synthetic.main.login_screen.*
import kotlinx.android.synthetic.main.register_screen.*
import org.jetbrains.anko.*
import org.wit.finance.R
import org.wit.finance.helpers.readImage
import org.wit.finance.helpers.readImageFromPath
import org.wit.finance.helpers.showImagePicker
import org.wit.finance.main.MainApp
import org.wit.finance.models.Location
import org.wit.finance.models.FinanceModel




class FinanceLogin : AppCompatActivity (), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        login_button.setOnClickListener(this)
        register_button.setOnClickListener {
            startActivity<FinanceRegister>()
        }
    }
    private fun validate() :Boolean {
        if (login_username.text.toString().isEmpty()) {
            login_username.error = "Please enter username!"
            return false
        } else if (login_password.text.toString().isEmpty()) {
            login_password.error = "Please enter password!"
            return false
        }
        return true

    }
    override fun onClick(v: View?){
    when(v?.id){
        R.id.login_button->{
            if (validate()){
                startActivity<FinanceListActivity>()
            }
        }

    }}
}



