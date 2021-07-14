package es.plexus.android.presentation_layer.domain

sealed class FailureBo {
    object Unauthorized : FailureBo()
    object NoNetwork : FailureBo()
    object Unknown : FailureBo()
    object NoData : FailureBo()
    object Request : FailureBo()
    class Exception(val type: String?, val message: String?) : FailureBo()
}

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