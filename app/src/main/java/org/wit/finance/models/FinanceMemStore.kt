package org.wit.finance.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class FinanceMemStore : FinanceStore, AnkoLogger {

    val finances = ArrayList<FinanceModel>()

    override fun findAll(): List<FinanceModel> {
        return finances
    }

    override fun create(finance: FinanceModel) {
        finance.id = getId()
        finances.add(finance)
        logAll()
    }

    override fun update(finance: FinanceModel) {
        var foundFinance: FinanceModel? = finances.find { p -> p.id == finance.id }
        if (foundFinance != null) {
            foundFinance.title = finance.title
            foundFinance.description = finance.description
            foundFinance.image = finance.image
            foundFinance.lat = finance.lat
            foundFinance.lng = finance.lng
            foundFinance.zoom = finance.zoom
            logAll();
        }
    }

    override fun delete(finance: FinanceModel) {
        finances.remove(finance)
    }



    fun logAll() {
        finances.forEach { info("${it}") }
    }
    override fun findById(id: Long): FinanceModel? {
        return finances.find { it.id == id }
    }
}

