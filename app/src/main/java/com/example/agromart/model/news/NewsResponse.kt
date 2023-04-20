package com.example.agromart.model.news

data class NewsResponse (
    val data: List<Datum> = listOf()
)

data class Datum (
    val title: String="",
    val author: String="",
    val publishedDate: String="",
    val publishedDatePrecision: PublishedDatePrecision,
    val link: String="",
    val cleanURL: String="",
    val excerpt: String="",
    val summary: String="",
    val rights: String="",
    val rank: Long,
    val topic: Topic,
    val country: Country,
    val language: Language,
    val authors: String="",
    val media: String="",
    val isOpinion: Boolean=false,
    val twitterAccount: String? = null,
    val score: Double=0.0,
    val id: String=""
)

enum class Country {
    In
}

enum class Language {
    En
}

enum class PublishedDatePrecision {
    Date,
    Full,
    TimezoneUnknown
}

enum class Topic {
    Business,
    News
}
