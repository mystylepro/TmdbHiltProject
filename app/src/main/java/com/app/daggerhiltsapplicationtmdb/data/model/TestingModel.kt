package com.app.daggerhiltsapplicationtmdb.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TestingModel(
    @Json(name = "ActionName")
    val actionName: String,
    @Json(name = "Data")
    val `data`: Data,
    @Json(name = "DataTable")
    val dataTable: List<Any>,
    @Json(name = "Message")
    val message: String,
    @Json(name = "Status")
    val status: String,
    @Json(name = "Success")
    val success: Boolean
)