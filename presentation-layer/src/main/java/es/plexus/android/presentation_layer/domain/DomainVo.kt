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
    val thumbnail: ThumbnailVo,
    val resourceURI: String,
    val comics: ComicsVo,
    val series: SeriesVo,
    val stories: StoriesVo,
    val events: EventsVo,
    val urls: List<UrlsVo>
)

data class ThumbnailVo(
    val path: String
)

data class SeriesVo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemsVo>,
    val returned: Int
)

data class ItemsVo(
    val resourceURI: String,
    val name: String
)

data class ComicsVo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemsVo>,
    val returned: Int
)

data class UrlsVo(
    val type: String,
    val url: String
)

data class StoriesVo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemsVo>,
    val returned: Int
)

data class EventsVo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemsVo>,
    val returned: Int
)