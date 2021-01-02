package org.wit.finance.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_finance.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.finance.helpers.readImage
import org.wit.finance.helpers.readImageFromPath
import org.wit.finance.helpers.showImagePicker
import org.wit.finance.main.MainApp
import org.wit.finance.models.Location
import org.wit.finance.models.FinanceModel
import org.wit.financeapp.R

class FinanceActivity : AppCompatActivity(), AnkoLogger {

    var finance = FinanceModel()
    lateinit var app: MainApp
    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2
    var location = Location(52.245696, -7.139102, 15f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finance)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        info("Finance Activity started..")

        app = application as MainApp
        var edit = false

        if (intent.hasExtra("finance_edit")) {
            edit = true
            finance = intent.extras?.getParcelable<FinanceModel>("finance_edit")!!
            financeTitle.setText(finance.title)
            description.setText(finance.description)
            financeImage.setImageBitmap(readImageFromPath(this, finance.image))
            if (finance.image != null) {
                chooseImage.setText(R.string.change_finance_image)
            }
            btnAdd.setText(R.string.save_finance)
        }

        btnAdd.setOnClickListener() {
            finance.title = financeTitle.text.toString()
            finance.description = description.text.toString()
            if (finance.title.isEmpty()) {
                toast(R.string.enter_finance_title)
            } else {
                if (edit) {
                    app.finances.update(finance.copy())
                } else {
                    app.finances.create(finance.copy())
                }
            }
            info("add Button Pressed: $financeTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

        financeLocation.setOnClickListener {
            startActivityForResult(intentFor<MapActivity>().putExtra("location", location), LOCATION_REQUEST)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_finance, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    finance.image = data.getData().toString()
                    financeImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_finance_image)
                }
            }
            LOCATION_REQUEST -> {
                if (data != null) {
                    location = data.extras?.getParcelable<Location>("location")!!
                }
            }
        }
    }
}

