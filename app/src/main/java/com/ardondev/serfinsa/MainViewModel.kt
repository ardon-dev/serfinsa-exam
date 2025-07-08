package com.ardondev.serfinsa

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ardondev.serfinsa.models.Commerce
import com.ardondev.serfinsa.models.Status
import java.util.UUID

class MainViewModel : ViewModel() {

    val name = mutableStateOf("")
    val department = mutableStateOf("")
    val municipality = mutableStateOf("")
    val document = mutableStateOf("")
    val phone = mutableStateOf("")
    val email = mutableStateOf("")
    val commerces = mutableStateOf<List<Commerce>>(listOf(
        Commerce(
            name = "Default",
            email = "default@mail.com",
            document = "01298240",
            phone = "77728292",
            department = "San Salvador",
            municipality = "San Salvador",
            id = UUID.randomUUID().toString(),
            status = Status.ACCEPTED
        )
    ))

    val validCommerce = mutableStateOf(false)

    fun validateCommerce() {
        validCommerce.value = (name.value.isNotEmpty() &&
                department.value.isNotEmpty() &&
                municipality.value.isNotEmpty() &&
                document.value.isNotEmpty() &&
                phone.value.isNotEmpty() &&
                email.value.isNotEmpty())
    }

    fun clear() {
        name.value = ""
        department.value = ""
        municipality.value = ""
        document.value = ""
        phone.value = ""
        email.value = ""
    }

    fun joinCommerce() {
        val newList = commerces.value.toMutableList().apply {
            add(
                Commerce(
                    id = UUID.randomUUID().toString(),
                    name = name.value,
                    email = email.value,
                    department = department.value,
                    municipality = municipality.value,
                    phone = phone.value,
                    document = document.value,
                    status = Status.PENDING
                )
            )
        }.toList()
        commerces.value = newList
    }

    fun approveCommerce(id: String) {
        val newList = commerces.value.toMutableList().apply {
            this.first { it.id == id }.status = Status.ACCEPTED
        }.toList()
        commerces.value = newList
    }

    fun rejectCommerce(id: String) {
        val newList = commerces.value.toMutableList().apply {
            this.first { it.id == id }.status = Status.REJECTED
        }.toList()
        commerces.value = newList
    }
}