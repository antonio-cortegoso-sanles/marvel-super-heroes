package es.plexus.android.domain_layer.usecase

import arrow.core.Either
import arrow.core.left
import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.ResultsBo

const val GET_SUPER_HERO_DETAIL_PERSISTED_UC_TAG = "getSuperHeroDetailPersistedUc"

class GetSuperHeroDetailUc(
    private val superHeroesRepository: DomainLayerContract.Data.SuperHeroesRepository
) : DomainLayerContract.Presentation.UseCase<Int, ResultsBo> {

    override suspend fun run(params: Int?): Either<FailureBo, ResultsBo> =
        params?.let { id ->
            superHeroesRepository.getSuperHeroesDetailPersistedData(id)
        } ?: FailureBo.NoData().left()

}
