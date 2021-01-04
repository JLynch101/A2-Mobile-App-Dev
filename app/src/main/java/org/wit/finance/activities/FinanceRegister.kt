package org.wit.finance.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_finance.*
import kotlinx.android.synthetic.main.login_screen.*
import kotlinx.android.synthetic.main.register_screen.*
import org.jetbrains.anko.*
import org.wit.finance.R

class FinanceRegister : AppCompatActivity (), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_screen)

        complete_button.setOnClickListener(this)

    }
    private fun validate() :Boolean {
        if (password_register.text.toString().isEmpty()) {
            password_register.error = "Please enter password!"
            return false
        } else if (username_register.text.toString().isEmpty()) {
            username_register.error = "Please enter username!"
            return false
        }
        return true

    }
    override fun onClick(v: View?){
        when(v?.id){
            R.id.complete_button->{
                if (validate()){
                    startActivity<FinanceLogin>()
                }
            }

        }}
}


