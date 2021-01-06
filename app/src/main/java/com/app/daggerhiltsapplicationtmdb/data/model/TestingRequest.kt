package com.app.daggerhiltsapplicationtmdb.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class TestingRequest(
    @Json(name = "ActionName")
    val actionName: String,
    @Json(name = "Parameters")
    val parameters: Parameters
)