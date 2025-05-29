package com.example.app_absencemanager

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.BaseAdapter
import android.widget.TextView

class AbsenceAdapter(private val context: Context, private val absences: List<Absence>) : BaseAdapter() {
    override fun getCount(): Int = absences.size
    override fun getItem(position: Int): Any = absences[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)
        val tv1 = view.findViewById<TextView>(android.R.id.text1)
        val tv2 = view.findViewById<TextView>(android.R.id.text2)

        val absence = absences[position]
        tv1.text = "${absence.nom} - ${absence.date}"
        tv2.text = "Raison : ${absence.raison}"

        return view
    }
}
