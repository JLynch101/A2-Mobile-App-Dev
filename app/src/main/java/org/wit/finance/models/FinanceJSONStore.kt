package org.wit.finance.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.finance.helpers.*
import java.util.*

val JSON_FILE = "finances.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<FinanceModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class FinanceJSONStore : FinanceStore, AnkoLogger {

    val context: Context
    var finances = mutableListOf<FinanceModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<FinanceModel> {
        return finances
    }

    override fun create(finance: FinanceModel) {
        finance.id = generateRandomId()
        finances.add(finance)
        serialize()
    }

    override fun update(finance: FinanceModel) {
        val financesList = findAll() as ArrayList<FinanceModel>
        var foundFinance: FinanceModel? = financesList.find { p -> p.id == finance.id }
        if (foundFinance != null) {
            foundFinance.title = finance.title
            foundFinance.description = finance.description
            foundFinance.image = finance.image
            foundFinance.lat = finance.lat
            foundFinance.lng = finance.lng
            foundFinance.zoom = finance.zoom
        }
        serialize()
    }

    override fun delete(finance: FinanceModel) {
        finances.remove(finance)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(finances, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        finances = Gson().fromJson(jsonString, listType)
    }
}

