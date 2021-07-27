package com.app.daggerhiltsapplicationtmdb.data.model

import com.squareup.moshi.Json

/*@Serializable
data class PopularMovieModel(
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
        @SerialName("backdrop_path")
        val backdropPath: String,
        @SerialName("genre_ids")
        val genreIds: List<Int>,
        @SerialName("id")
        val id: Int,
        @SerialName("original_language")
        val originalLanguage: String,
        @SerialName("original_title")
        val originalTitle: String,
        @SerialName("overview")
        val overview: String,
        @SerialName("popularity")
        val popularity: Double,
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
}*/

class PopularMovieModel(
    @Json(name = "page") val page: Int,
    @Json(name = "total_results") val total_results: Int,
    @Json(name = "total_pages") val total_pages: Int,
    @Json(name = "results") val results: List<Results>
) {
    data class Results(
        @Json(name = "popularity") val popularity: Double,
        @Json(name = "vote_count") val vote_count : Int,
        @Json(name = "video") val video : Boolean,
        @Json(name = "poster_path") val poster_path : String,
        @Json(name = "id") val id : Int,
        @Json(name = "adult") val adult : Boolean,
        @Json(name = "backdrop_path") val backdrop_path : String,
        @Json(name = "original_language") val original_language : String,
        @Json(name = "original_title") val original_title : String,
        @Json(name = "genre_ids") val genre_ids : List<Int>,
        @Json(name = "title") val title : String,
        @Json(name = "vote_average") val vote_average : Double,
        @Json(name = "overview") val overview : String,
        @Json(name = "release_date") val release_date : String
    )
}
