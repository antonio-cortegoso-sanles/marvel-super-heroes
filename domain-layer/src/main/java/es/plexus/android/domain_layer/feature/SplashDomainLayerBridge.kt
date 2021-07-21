package es.plexus.android.domain_layer.feature

import arrow.core.Either
import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.domain_layer.base.BaseDomainLayerBridge
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo

const val SPLASH_BRIDGE_TAG = "splashDomainLayerBridge"

interface SplashDomainLayerBridge : BaseDomainLayerBridge {
    suspend fun synchronizeSuperHeroesList(): Either<FailureBo, Boolean>
    suspend fun getSuperHeroesList(): Either<FailureBo, SuperHeroesDataBo>
}

internal class SplashDomainLayerBridgeImpl(
    private val synchronizeSuperHeroesListUc: DomainLayerContract.Presentation.UseCase<Nothing, Boolean>,
    private val getSuperHeroesListUc: DomainLayerContract.Presentation.UseCase<Nothing, SuperHeroesDataBo>,
) : SplashDomainLayerBridge {

    override suspend fun synchronizeSuperHeroesList(): Either<FailureBo, Boolean> =
        synchronizeSuperHeroesListUc.run()

    override suspend fun getSuperHeroesList(): Either<FailureBo, SuperHeroesDataBo> =
        getSuperHeroesListUc.run()

}