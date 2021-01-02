package org.wit.finance.models

interface FinanceStore {
    fun findAll(): List<FinanceModel>
    fun create(finance: FinanceModel)
    fun update(finance: FinanceModel)
    fun delete(finance: FinanceModel)
}

