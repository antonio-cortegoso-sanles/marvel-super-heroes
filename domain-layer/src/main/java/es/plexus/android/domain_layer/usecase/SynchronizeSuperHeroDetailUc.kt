package es.plexus.android.domain_layer.usecase

import arrow.core.Either
import arrow.core.left
import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.domain_layer.domain.FailureBo

const val SYNCHRONIZE_SUPER_HERO_DETAIL_UC_TAG = "synchronizeSuperHeroDetailUc"

internal class SynchronizeSuperHeroDetailUc(
    private val superHeroesRepository: DomainLayerContract.Data.SuperHeroesRepository
) : DomainLayerContract.Presentation.UseCase<Int, Int> {

    override suspend fun run(params: Int?): Either<FailureBo, Int> =
        params?.let { id ->
            superHeroesRepository.fetchSuperHeroDetailData(id)
        }?:FailureBo.NoData().left()

}
