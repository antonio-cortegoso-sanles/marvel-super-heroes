package es.plexus.android.presentation_layer.domain

import es.plexus.android.domain_layer.domain.*


fun SuperHeroesDataBo.toVo(): SuperHeroesDataVo =
    SuperHeroesDataVo(this.results.map { it.toVo() })

fun ResultsBo.toVo(): ResultsVo =
    ResultsVo(
        this.id,
        this.name,
        checkHeroDescription(this.description),
        this.modified,
        this.thumbnail.toVo(),
        this.resourceURI,
        this.comics.toVo(),
        this.series.toVo(),
        this.stories.toVo(),
        this.events.toVo(),
        this.urls.map { it.toVo() }
    )

fun ThumbnailBo.toVo(): ThumbnailVo =
    ThumbnailVo(this.path + "." + this.extension)

fun SeriesBo.toVo(): SeriesVo =
    SeriesVo(this.available, this.collectionURI, this.items.map { it.toVo() }, this.returned)

fun ItemsBo.toVo(): ItemsVo =
    ItemsVo(this.resourceURI, this.name)

fun ComicsBo.toVo(): ComicsVo =
    ComicsVo(this.available, this.collectionURI, this.items.map { it.toVo() }, this.returned)

fun UrlsBo.toVo(): UrlsVo =
    UrlsVo(this.type, this.url)

fun StoriesBo.toVo(): StoriesVo =
    StoriesVo(this.available, this.collectionURI, this.items.map { it.toVo() }, this.returned)

fun EventsBo.toVo(): EventsVo =
    EventsVo(this.available, this.collectionURI, this.items.map { it.toVo() }, this.returned)

private fun checkHeroDescription(description: String): String =
    if (description.isEmpty() or description.isBlank())
        "Shield Database has no data for this hero"
    else
        description