package com.app.daggerhiltsapplicationtmdb.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Parameters(
    @Json(name = "IsTest")
    val isTest: Boolean,
    @Json(name = "Mobile")
    val mobile: String
)