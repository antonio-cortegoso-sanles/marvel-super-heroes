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
        this.picture,
        this.resourceURI,
        this.comicsNumber,
        this.seriesNumber,
        this.storiesNumber,
        this.eventsNumber,
        this.urls.map { it.toVo() }
    )

fun UrlsBo.toVo(): UrlsVo =
    UrlsVo(this.type, this.url)

private fun checkHeroDescription(description: String): String =
    if (description.isEmpty() or description.isBlank())
        "Shield Database has no data for this hero"
    else
        description