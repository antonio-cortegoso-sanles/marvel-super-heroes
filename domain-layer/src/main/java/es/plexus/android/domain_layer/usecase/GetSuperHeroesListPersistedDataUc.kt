package es.plexus.android.domain_layer.usecase

import arrow.core.Either
import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo

const val GET_SUPER_HEROES_LIST_PERSISTED_UC_TAG = "getSuperHeroesListPersistedUc"

class GetSuperHeroesListPersistedDataUc(
    private val superHeroesRepository: DomainLayerContract.Data.SuperHeroesRepository
) : DomainLayerContract.Presentation.UseCase<Any, SuperHeroesDataBo> {

    override suspend fun run(params: Any?): Either<FailureBo, SuperHeroesDataBo> =
        superHeroesRepository.getSuperHeroesListPersistedData()
}
