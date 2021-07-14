package es.plexus.android.data_layer.contract

import arrow.core.Either
import es.plexus.android.data_layer.db.HeroDetailEntity
import es.plexus.android.data_layer.db.HeroEntity
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.ResultsBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo

interface DataLayerContract {

    interface SuperHeroesDataSource {

        companion object {
            const val API_SERVICE_TAG = "superHeroesApiService"
            const val API_BASE_URL = "https://gateway.marvel.com:443/v1/public/"
        }

        interface Remote {
            suspend fun fetchSuperHeroesListData(): Either<FailureBo, SuperHeroesDataBo>
            suspend fun fetchSuperHeroDetailData(id: Int): Either<FailureBo, SuperHeroesDataBo>
        }

        interface Persistence {
            suspend fun saveSuperHeroesListData(data: ArrayList<HeroEntity>): Either<FailureBo, Boolean>
            suspend fun saveSuperHeroesDetailData(data: HeroDetailEntity): Either<FailureBo, Int>
            suspend fun getSuperHeroesListData(): Either<FailureBo, List<HeroEntity>>
            suspend fun getSuperHeroDetailData(id: Int): Either<FailureBo, HeroDetailEntity>
        }
    }

}