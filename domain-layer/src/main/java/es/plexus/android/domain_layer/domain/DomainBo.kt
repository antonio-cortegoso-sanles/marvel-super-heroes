package es.plexus.android.domain_layer.domain

const val DEFAULT_STRING: String = ""
const val DEFAULT_INTEGER: Int = -1

sealed class FailureBo(val message: String, val code: Int) {

    class Unauthorized(message: String = DEFAULT_STRING, code: Int = DEFAULT_INTEGER) :
        FailureBo(message, code)

    class Request(message: String = DEFAULT_STRING, code: Int = DEFAULT_INTEGER) :
        FailureBo(message, code)

    class NoNetwork(message: String = DEFAULT_STRING, code: Int = DEFAULT_INTEGER) :
        FailureBo(message, code)

    class Unknown(message: String = DEFAULT_STRING, code: Int = DEFAULT_INTEGER) :
        FailureBo(message, code)

    class NoData(message: String = DEFAULT_STRING, code: Int = DEFAULT_INTEGER) :
        FailureBo(message, code)

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
