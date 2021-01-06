package com.app.daggerhiltsapplicationtmdb.data.model

import com.squareup.moshi.Json

data class PopularArtistModel (

    @Json(name = "page") val page : Int,
    @Json(name = "total_results") val total_results : Int,
    @Json(name = "total_pages") val total_pages : Int,
    @Json(name = "results") val results : List<Results>
)
{
    data class Results (
        @Json(name = "popularity") val popularity : Double,
        @Json(name = "known_for_department") val known_for_department : String,
        @Json(name = "gender") val gender : Int,
        @Json(name = "id") val id : Int,
        @Json(name = "profile_path") val profile_path : String,
        @Json(name = "adult") val adult : Boolean,
        @Json(name = "known_for") val known_for : List<Known_for>,
        @Json(name = "name") val name : String
    )
    {
        data class Known_for (
            @Json(name = "release_date") val release_date : String,
            @Json(name = "id") val id : Int,
            @Json(name = "vote_count") val vote_count : Int,
            @Json(name = "video") val video : Boolean,
            @Json(name = "media_type") val media_type : String,
            @Json(name = "vote_average") val vote_average : Double,
            @Json(name = "title") val title : String,
            @Json(name = "genre_ids") val genre_ids : List<Int>,
            @Json(name = "original_title") val original_title : String,
            @Json(name = "original_language") val original_language : String,
            @Json(name = "adult") val adult : Boolean,
            @Json(name = "backdrop_path") val backdrop_path : String,
            @Json(name = "overview") val overview : String,
            @Json(name = "poster_path") val poster_path : String
        )
    }
}