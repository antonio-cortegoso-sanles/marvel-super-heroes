package es.plexus.android.presentation_layer.domain

data class SuperHeroesDataVo(val results: List<ResultsVo>)

data class ResultsVo(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val picture: String,
    val resourceURI: String,
    val comicsNumber: String,
    val seriesNumber: String,
    val storiesNumber: String,
    val eventsNumber: String,
    val urls: List<UrlsVo>
)

data class UrlsVo(
    val type: String,
    val url: String
)