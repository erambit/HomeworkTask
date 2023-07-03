package com.example.homework.data.api.model.user


import com.example.homework.data.api.model.user.Address
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "address")
    val address: Address,
    @Json(name = "company")
    val company: Company,
    @Json(name = "email")
    val email: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "username")
    val username: String,
    @Json(name = "website")
    val website: String
)