package com.example.app_absencemanager

import java.io.Serializable

data class Absence(
    val nom: String,
    val date: String,
    val raison: String
) : Serializable
