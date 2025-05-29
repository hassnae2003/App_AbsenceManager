package com.example.app_absencemanager

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ArchiveActivity : AppCompatActivity() {

    private lateinit var listViewArchive: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)

        listViewArchive = findViewById(R.id.listViewArchive)

        val absences = intent.getSerializableExtra("listeAbsences") as? ArrayList<Absence> ?: arrayListOf()
        val adapter = AbsenceAdapter(this, absences)
        listViewArchive.adapter = adapter
    }
}
