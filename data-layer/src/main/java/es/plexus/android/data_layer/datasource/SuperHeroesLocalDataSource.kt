package es.plexus.android.data_layer.datasource

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.plexus.android.data_layer.contract.DataLayerContract
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo

class SuperHeroesLocalDataSource() : DataLayerContract.SuperHeroesDataSource.Local {

    private var cachedData : SuperHeroesDataBo? = null
    private var cachedDetail : SuperHeroesDataBo? = null

    override suspend fun saveSuperHeroesListData(data : SuperHeroesDataBo): Either<FailureBo, Boolean> {
        cachedData = data
        return true.right()
    }

    override suspend fun saveSuperHeroesDetailData(data : SuperHeroesDataBo): Either<FailureBo, Boolean> {
        cachedDetail = data
        return true.right()
    }

    override suspend fun getSuperHeroesListData(): Either<FailureBo, SuperHeroesDataBo> =
        cachedData?.right() ?:FailureBo.NoData.left()

    override suspend fun getSuperHeroDetailData(id : Int): Either<FailureBo, SuperHeroesDataBo> =
        cachedData?.right() ?:FailureBo.NoData.left()
}