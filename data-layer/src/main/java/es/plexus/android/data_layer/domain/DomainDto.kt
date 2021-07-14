package es.plexus.android.data_layer.domain

import com.squareup.moshi.Json


const val NO_NETWORK_CODE = 500
const val UNKNOWN_CODE = -1
const val DEFAULT_MESSAGE: String = ""
const val UNKNOWN_ERROR_MSG = "Unknown Error"
const val NO_NETWORK_MSG = "No network available"
const val NO_DATA_MSG = "No data available"

sealed class FailureDto(val message: String, val code: Int) {

    class Unauthorized(message: String = DEFAULT_MESSAGE, code: Int = UNKNOWN_CODE) :
        FailureDto(message, code)

    class Request(message: String, code: Int) : FailureDto(message, code)
    object NoNetwork : FailureDto(NO_NETWORK_MSG, NO_NETWORK_CODE)
    object Unknown : FailureDto(UNKNOWN_ERROR_MSG, UNKNOWN_CODE)
    object NoData : FailureDto(NO_DATA_MSG, UNKNOWN_CODE)

}

data class SuperHeroesDto(
    @Json(name = "data") val data: SuperHeroesDataDto
)

data class SuperHeroesDataDto(
    @Json(name = "offset") val offset: Int,
    @Json(name = "limit") val limit: Int,
    @Json(name = "total") val total: Int,
    @Json(name = "count") val count: Int,
    @Json(name = "results") val results: List<ResultsDto>
)

data class ResultsDto(

    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "modified") val modified: String,
    @Json(name = "thumbnail") val thumbnail: ThumbnailDto,
    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "comics") val comics: ComicsDto,
    @Json(name = "series") val series: SeriesDto,
    @Json(name = "stories") val stories: StoriesDto,
    @Json(name = "events") val events: EventsDto,
    @Json(name = "urls") val urls: List<UrlsDto>
)

data class ThumbnailDto(

    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
)

data class SeriesDto(

    @Json(name = "available") val available: Int,
    @Json(name = "collectionURI") val collectionURI: String,
    @Json(name = "items") val items: List<ItemsDto>,
    @Json(name = "returned") val returned: Int
)

data class ItemsDto(

    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "name") val name: String
)

data class ComicsDto(

    @Json(name = "available") val available: Int,
    @Json(name = "collectionURI") val collectionURI: String,
    @Json(name = "items") val items: List<ItemsDto>,
    @Json(name = "returned") val returned: Int
)

data class UrlsDto(

    @Json(name = "type") val type: String,
    @Json(name = "url") val url: String
)

data class StoriesDto(

    @Json(name = "available") val available: Int,
    @Json(name = "collectionURI") val collectionURI: String,
    @Json(name = "items") val items: List<ItemsDto>,
    @Json(name = "returned") val returned: Int
)

data class EventsDto(

    @Json(name = "available") val available: Int,
    @Json(name = "collectionURI") val collectionURI: String,
    @Json(name = "items") val items: List<ItemsDto>,
    @Json(name = "returned") val returned: Int
)