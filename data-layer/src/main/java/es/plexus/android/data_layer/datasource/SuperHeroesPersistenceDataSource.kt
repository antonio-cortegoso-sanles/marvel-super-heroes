package es.plexus.android.data_layer.datasource

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.plexus.android.data_layer.contract.DataLayerContract
import es.plexus.android.data_layer.db.HeroDetailEntity
import es.plexus.android.data_layer.db.HeroEntity
import es.plexus.android.data_layer.db.HeroesDatabase
import es.plexus.android.data_layer.domain.FailureDto
import es.plexus.android.data_layer.domain.dtoToBoFailure
import es.plexus.android.domain_layer.domain.FailureBo

internal class SuperHeroesPersistenceDataSource(
    private val database: HeroesDatabase
) : DataLayerContract.SuperHeroesDataSource.Persistence {

    override suspend fun saveSuperHeroesListData(data: ArrayList<HeroEntity>): Either<FailureBo, Boolean> {
        data.forEach { hero ->
            database.appDao().insertHero(hero)
        }
        return true.right()
    }

    override suspend fun saveSuperHeroesDetailData(data: HeroDetailEntity): Either<FailureBo, Int> {
        database.appDao().insertHeroDetail(data)
        return data.heroId.right()
    }

    override suspend fun getSuperHeroesListData(): Either<FailureBo, List<HeroEntity>> =
        database.appDao().getHeroesList().right()

    override suspend fun getSuperHeroDetailData(id: Int): Either<FailureBo, HeroDetailEntity> =
        database.appDao().getHeroDetail(id)?.right() ?: FailureDto.NoData.dtoToBoFailure().left()
}
