package es.plexus.android.domain_layer.feature

import arrow.core.Either
import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.domain_layer.base.BaseDomainLayerBridge
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo

const val SPLASH_BRIDGE_TAG = "splashDomainLayerBridge"

interface SplashDomainLayerBridge : BaseDomainLayerBridge {
    suspend fun fetchSuperHeroesList(): Either<FailureBo, Boolean>
}

internal class SplashDomainLayerBridgeImpl(
    private val fetchSuperHeroesListUc: DomainLayerContract.Presentation.UseCase<Nothing, Boolean>,
) : SplashDomainLayerBridge{

    override suspend fun fetchSuperHeroesList(): Either<FailureBo, Boolean> =
        fetchSuperHeroesListUc.run()
}