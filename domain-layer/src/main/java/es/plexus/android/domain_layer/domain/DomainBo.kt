package es.plexus.android.domain_layer.domain

const val DEFAULT_STRING: String = ""
const val DEFAULT_INT: Int = 0

sealed class FailureBo {
    object Unauthorized : FailureBo()
    object NoNetwork : FailureBo()
    object Unknown : FailureBo()
    object NoData : FailureBo()
    object Request : FailureBo()
    class Exception(val type: String?, val message: String?) : FailureBo()
}

data class SuperHeroesDataBo(val results: List<ResultsBo>)

data class ResultsBo(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String = DEFAULT_STRING,
    val picture: String = DEFAULT_STRING,
    val resourceURI: String = DEFAULT_STRING,
    val comicsNumber: String = DEFAULT_STRING,
    val seriesNumber: String = DEFAULT_STRING,
    val storiesNumber: String = DEFAULT_STRING,
    val eventsNumber: String = DEFAULT_STRING,
    val urls: List<UrlsBo> = emptyList()
)

data class UrlsBo(
    val type: String = DEFAULT_STRING,
    val url: String = DEFAULT_STRING
)
