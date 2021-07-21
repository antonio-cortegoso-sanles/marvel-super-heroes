package es.plexus.android.domain_layer.usecase

import arrow.core.Either
import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.domain_layer.domain.FailureBo

const val SYNCHRONIZE_SUPER_HEROES_LIST_UC_TAG = "synchronizeSuperHeroesListUc"

internal class SynchronizeSuperHeroesListDataUc(
    private val superHeroesRepository: DomainLayerContract.Data.SuperHeroesRepository
) : DomainLayerContract.Presentation.UseCase<Any, Boolean> {

    override suspend fun run(params: Any?): Either<FailureBo, Boolean> =
        superHeroesRepository.fetchSuperHeroesListData()
}
