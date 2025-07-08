package com.ardondev.serfinsa

import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

@Serializable
object  HomeRoute

@Serializable
object JoinCommerceRoute

@Serializable
data class CommerceDetailRoute(val id: String)