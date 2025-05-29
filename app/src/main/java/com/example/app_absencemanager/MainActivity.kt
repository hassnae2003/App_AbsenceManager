package com.example.app_absencemanager

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNom: EditText
    private lateinit var etDate: EditText
    private lateinit var etRaison: EditText
    private lateinit var btnAjouter: Button
    private lateinit var listView: ListView

    private val listeAbsences = mutableListOf<Absence>()
    private lateinit var adapter: AbsenceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNom = findViewById(R.id.etNom)
        etDate = findViewById(R.id.etDate)
        etRaison = findViewById(R.id.etRaison)
        btnAjouter = findViewById(R.id.btnAjouter)
        listView = findViewById(R.id.listView)

        adapter = AbsenceAdapter(this, listeAbsences)
        listView.adapter = adapter

        btnAjouter.setOnClickListener {
            val nom = etNom.text.toString()
            val date = etDate.text.toString()
            val raison = etRaison.text.toString()

            if (nom.isNotEmpty() && date.isNotEmpty() && raison.isNotEmpty()) {
                val absence = Absence(nom, date, raison)
                listeAbsences.add(absence)
                adapter.notifyDataSetChanged()

                etNom.text.clear()
                etDate.text.clear()
                etRaison.text.clear()
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
