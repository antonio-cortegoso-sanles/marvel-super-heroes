package es.plexus.android.data_layer.repository

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.data_layer.contract.DataLayerContract
import es.plexus.android.data_layer.domain.toBo
import es.plexus.android.data_layer.domain.toEntity
import es.plexus.android.data_layer.domain.toEntityList
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.ResultsBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo

class SuperHeroesRepository(
    private val remoteDataSource: DataLayerContract.SuperHeroesDataSource.Remote,
    private val persistenceDataSource: DataLayerContract.SuperHeroesDataSource.Persistence
) : DomainLayerContract.Data.SuperHeroesRepository {

    override suspend fun fetchSuperHeroesListData(): Either<FailureBo, Boolean> =
        remoteDataSource.fetchSuperHeroesListData().flatMap { data ->
            saveSuperHeroesListData(data)
        }

    override suspend fun fetchSuperHeroDetailData(id: Int): Either<FailureBo, Int> =
        remoteDataSource.fetchSuperHeroDetailData(id).flatMap { data ->
            saveSuperHeroesDetailData(data.results.first())
        }

    override suspend fun saveSuperHeroesListData(data: SuperHeroesDataBo): Either<FailureBo, Boolean> =
        persistenceDataSource.saveSuperHeroesListData(data.toEntityList())

    override suspend fun saveSuperHeroesDetailData(data: ResultsBo): Either<FailureBo, Int> =
        persistenceDataSource.saveSuperHeroesDetailData(data.toEntity())

    override suspend fun getSuperHeroesListPersistedData(): Either<FailureBo, SuperHeroesDataBo> =
        persistenceDataSource.getSuperHeroesListData().flatMap { response ->
            response.toBo().right()
        }

    override suspend fun getSuperHeroesDetailPersistedData(id: Int): Either<FailureBo, ResultsBo> {
        val response = persistenceDataSource.getSuperHeroDetailData(id)
        if (response.isRight()) {
            response.map {
                return it.toBo().right()
            }
        }
        return FailureBo.NoData().left()

    }
}