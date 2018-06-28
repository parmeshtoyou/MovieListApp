package com.example.parmeshmahore.movielistdemo.model

class MovieResponse {

    var results: List<Movie>? = null

    var page: String? = null

    var total_pages: String? = null

    var total_results: String? = null

    override fun toString(): String {
        return "Movie [results = $results, page = $page, total_pages = $total_pages, total_results = $total_results]"
    }
}