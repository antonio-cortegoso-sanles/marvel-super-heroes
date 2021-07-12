package es.plexus.android.domain_layer.domain

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
    val modified: String,
    val thumbnail: ThumbnailBo,
    val resourceURI: String,
    val comics: ComicsBo,
    val series: SeriesBo,
    val stories: StoriesBo,
    val events: EventsBo,
    val urls: List<UrlsBo>
)

data class ThumbnailBo(
    val path: String, val extension: String
)

data class SeriesBo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemsBo>,
    val returned: Int
)

data class ItemsBo(
    val resourceURI: String,
    val name: String
)

data class ComicsBo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemsBo>,
    val returned: Int
)

data class UrlsBo(
    val type: String,
    val url: String
)

data class StoriesBo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemsBo>,
    val returned: Int
)

data class EventsBo(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemsBo>,
    val returned: Int
)