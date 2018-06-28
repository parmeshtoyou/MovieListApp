package com.example.parmeshmahore.movielistdemo.model

class Movie {
    var vote_average: String? = null

    var backdrop_path: String? = null

    var adult: String? = null

    var id: String? = null

    var title: String? = null

    var overview: String? = null

    var original_language: String? = null

    var genre_ids: Array<String>? = null

    var release_date: String? = null

    var original_title: String? = null

    var vote_count: String? = null

    var poster_path: String? = null

    var video: String? = null

    var popularity: String? = null

    override fun toString(): String {
        return "Results [vote_average = $vote_average, backdrop_path = $backdrop_path, adult = $adult, id = $id, title = $title, overview = $overview, original_language = $original_language, genre_ids = $genre_ids, release_date = $release_date, original_title = $original_title, vote_count = $vote_count, poster_path = $poster_path, video = $video, popularity = $popularity]"
    }
}