package com.app.daggerhiltsapplicationtmdb.data.model

import com.squareup.moshi.Json

/*
@Serializable
data class PopularArtistModel(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<Results>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
) {
    @Serializable
    data class Results(
        @SerialName("adult")
        val adult: Boolean,
        @SerialName("gender")
        val gender: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("known_for")
        val knownFor: List<KnownFor>,
        @SerialName("known_for_department")
        val knownForDepartment: String,
        @SerialName("name")
        val name: String,
        @SerialName("popularity")
        val popularity: Double,
        @SerialName("profile_path")
        val profilePath: String
    ) {
        @Serializable
        data class KnownFor(
            @SerialName("adult")
            val adult: Boolean,
            @SerialName("backdrop_path")
            val backdropPath: String,
            @SerialName("first_air_date")
            val firstAirDate: String,
            @SerialName("genre_ids")
            val genreIds: List<Int>,
            @SerialName("id")
            val id: Int,
            @SerialName("media_type")
            val mediaType: String,
            @SerialName("name")
            val name: String,
            @SerialName("origin_country")
            val originCountry: List<String>,
            @SerialName("original_language")
            val originalLanguage: String,
            @SerialName("original_name")
            val originalName: String,
            @SerialName("original_title")
            val originalTitle: String,
            @SerialName("overview")
            val overview: String,
            @SerialName("poster_path")
            val posterPath: String,
            @SerialName("release_date")
            val releaseDate: String,
            @SerialName("title")
            val title: String,
            @SerialName("video")
            val video: Boolean,
            @SerialName("vote_average")
            val voteAverage: Double,
            @SerialName("vote_count")
            val voteCount: Int
        )
    }
}
*/

data class PopularArtistModel(

    @Json(name = "page") val page: Int,
    @Json(name = "total_results") val total_results: Int,
    @Json(name = "total_pages") val total_pages: Int,
    @Json(name = "results") val results: List<Results>
) {
    data class Results(
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
