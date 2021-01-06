package com.app.daggerhiltsapplicationtmdb.data.model

import com.squareup.moshi.Json

data class PopularTvShowModel(
    @Json(name = "page") val page : Int,
    @Json(name = "total_results") val total_results : Int,
    @Json(name = "total_pages") val total_pages : Int,
    @Json(name = "results") val results : List<Results>
)
{
    data class Results (

        @Json(name = "original_name") val original_name : String,
        @Json(name = "genre_ids") val genre_ids : List<Int>,
        @Json(name = "name") val name : String,
        @Json(name = "popularity") val popularity : Double,
        @Json(name = "origin_country") val origin_country : List<String>,
        @Json(name = "vote_count") val vote_count : Int,
        @Json(name = "first_air_date") val first_air_date : String,
        @Json(name = "backdrop_path") val backdrop_path : String,
        @Json(name = "original_language") val original_language : String,
        @Json(name = "id") val id : Int,
        @Json(name = "vote_average") val vote_average : Double,
        @Json(name = "overview") val overview : String,
        @Json(name = "poster_path") val poster_path : String
    )
}