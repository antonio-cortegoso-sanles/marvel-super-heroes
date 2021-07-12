package es.plexus.android.data_layer.domain

import es.plexus.android.domain_layer.domain.*

fun SuperHeroesDto.toBo() : SuperHeroesDataBo =
    SuperHeroesDataBo(this.data.results.map { it.toBo() } )

fun SuperHeroesDataDto.toBo() : SuperHeroesDataBo =
    SuperHeroesDataBo(this.results.map { it.toBo() })

fun ResultsDto.toBo(): ResultsBo =
    ResultsBo(
        this.id,
        this.name,
        this.description,
        this.modified,
        this.thumbnail.toBo(),
        this.resourceURI,
        this.comics.toBo(),
        this.series.toBo(),
        this.stories.toBo(),
        this.events.toBo(),
        this.urls.map { it.toBo() }
    )

fun ThumbnailDto.toBo(): ThumbnailBo =
    ThumbnailBo(this.path, this.extension)

fun SeriesDto.toBo(): SeriesBo =
    SeriesBo(this.available, this.collectionURI, this.items.map { it.toBo() }, this.returned)

fun ItemsDto.toBo(): ItemsBo =
    ItemsBo(this.resourceURI, this.name)

fun ComicsDto.toBo(): ComicsBo =
    ComicsBo(this.available, this.collectionURI, this.items.map { it.toBo() }, this.returned)

fun UrlsDto.toBo(): UrlsBo =
    UrlsBo(this.type, this.url)

fun StoriesDto.toBo(): StoriesBo =
    StoriesBo(this.available, this.collectionURI, this.items.map { it.toBo() }, this.returned)

fun EventsDto.toBo(): EventsBo =
    EventsBo(this.available, this.collectionURI, this.items.map { it.toBo() }, this.returned)