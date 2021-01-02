package org.wit.finance.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_finance.view.*
import org.wit.finance.R
import org.wit.finance.helpers.readImageFromPath
import org.wit.finance.models.FinanceModel

interface FinanceListener {
    fun onFinanceClick(finance: FinanceModel)
}

class FinanceAdapter constructor(private var finances: List<FinanceModel>,
                                 private val listener: FinanceListener) : RecyclerView.Adapter<FinanceAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_finance, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val finance = finances[holder.adapterPosition]
        holder.bind(finance, listener)
    }

    override fun getItemCount(): Int = finances.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(finance: FinanceModel, listener: FinanceListener) {
            itemView.financeTitle.text = finance.title
            itemView.description.text = finance.description
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, finance.image))
            itemView.setOnClickListener { listener.onFinanceClick(finance) }
        }
    }
}


