package org.wit.finance.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finance.*
import kotlinx.android.synthetic.main.login_screen.*
import kotlinx.android.synthetic.main.register_screen.*
import org.jetbrains.anko.*
import org.wit.finance.R

class FinanceRegister : AppCompatActivity () {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_screen)

        login1_button.setOnClickListener {
            startActivity<FinanceLogin>()
        }
    }
}
