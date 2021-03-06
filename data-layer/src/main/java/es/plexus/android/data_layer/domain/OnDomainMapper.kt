package es.plexus.android.data_layer.domain

import es.plexus.android.data_layer.db.HeroDetailEntity
import es.plexus.android.data_layer.db.HeroEntity
import es.plexus.android.domain_layer.domain.*

internal fun SuperHeroesDto.toBo(): SuperHeroesDataBo =
    SuperHeroesDataBo(this.data.results.map { it.toBo() })

internal fun ResultsDto.toBo(): ResultsBo =
    ResultsBo(
        this.id,
        this.name,
        this.description,
        this.modified,
        this.thumbnail.toBo(),
        this.resourceURI,
        this.comics.available.toString(),
        this.series.available.toString(),
        this.stories.available.toString(),
        this.events.available.toString(),
        this.urls.map { it.toBo() }
    )

internal fun ThumbnailDto.toBo(): String = this.path + "." + this.extension

internal fun UrlsDto.toBo(): UrlsBo =
    UrlsBo(this.type, this.url)

internal fun SuperHeroesDataBo.toEntityList(): ArrayList<HeroEntity> {
    val entities = arrayListOf<HeroEntity>()

    this.results.forEach { result ->
        entities.add(
            HeroEntity(
                result.id,
                result.name,
                result.description,
                result.picture
            )
        )
    }
    return entities
}

internal fun ResultsBo.toEntity(): HeroDetailEntity =
    HeroDetailEntity(
        this.id,
        this.name,
        this.description,
        this.seriesNumber,
        this.comicsNumber,
        this.storiesNumber,
        this.eventsNumber,
        this.picture,
        this.modified
    )


internal fun List<HeroEntity>.toBo(): SuperHeroesDataBo {
    val results = arrayListOf<ResultsBo>()
    this.forEach { hero ->
        results.add(
            ResultsBo(
                id = hero.heroId,
                name = hero.heroName,
                description = hero.heroDescription,
                picture = hero.heroPicture
            )
        )
    }
    return SuperHeroesDataBo(results)
}

internal fun HeroDetailEntity.toBo(): ResultsBo =
    ResultsBo(
        id = this.heroId,
        name = this.heroName,
        description = this.heroDescription,
        modified = this.lastUpdate,
        picture = this.heroPicture,
        comicsNumber = this.comicsNumber,
        seriesNumber = this.seriesNumber,
        storiesNumber = this.storiesNumber,
        eventsNumber = this.eventsNumber
    )