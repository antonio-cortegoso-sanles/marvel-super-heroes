package es.plexus.android.domain_layer.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo

const val FETCH_SUPER_HERO_DETAIL_UC_TAG = "fetchSuperHeroDetailUc"

class FetchSuperHeroDetailUc(
    private val superHeroesRepository: DomainLayerContract.Data.SuperHeroesRepository
) : DomainLayerContract.Presentation.UseCase<Int, Boolean> {

    override suspend fun run(params: Int?): Either<FailureBo, Boolean> =
        params?.let { id ->
            superHeroesRepository.fetchSuperHeroDetailData(id)
        }?:FailureBo.NoData.left()

}
