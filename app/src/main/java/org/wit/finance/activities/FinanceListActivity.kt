package org.wit.finance.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_finance_list.*
import kotlinx.android.synthetic.main.card_finance.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.finance.R
import org.wit.finance.main.MainApp
import org.wit.finance.models.FinanceModel

class FinanceListActivity : AppCompatActivity(), FinanceListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finance_list)
        app = application as MainApp
        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = FinanceAdapter(app.finances.findAll(), this)
        loadFinances()
    }

    private fun loadFinances() {
        showFinances( app.finances.findAll())
    }

    fun showFinances (finances: List<FinanceModel>) {
        recyclerView.adapter = FinanceAdapter(finances, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<FinanceActivity>(200)
            R.id.item_map -> startActivity<FinanceMapsActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFinanceClick(finance: FinanceModel) {
        startActivityForResult(intentFor<FinanceActivity>().putExtra("finance_edit", finance), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadFinances()
        super.onActivityResult(requestCode, resultCode, data)
    }
}

