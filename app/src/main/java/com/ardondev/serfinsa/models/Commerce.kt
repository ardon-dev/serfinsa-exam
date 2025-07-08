package com.ardondev.serfinsa.models

data class Commerce(
    val id: String,
    val name: String,
    val department: String,
    val municipality: String,
    val document: String,
    val phone: String,
    val email: String,
    var status: Status
)

enum class Status(val title: String) {
    PENDING("Pendiente"),
    ACCEPTED("Aceptado"),
    REJECTED("Rechazado")
}
