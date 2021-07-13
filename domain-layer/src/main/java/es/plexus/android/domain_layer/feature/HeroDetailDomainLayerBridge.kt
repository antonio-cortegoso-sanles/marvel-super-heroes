package es.plexus.android.domain_layer.feature

import arrow.core.Either
import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.domain_layer.base.BaseDomainLayerBridge
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.ResultsBo

const val HERO_DETAIL_BRIDGE_TAG = "heroDetailDomainLayerBridge"

interface HeroDetailDomainLayerBridge : BaseDomainLayerBridge {
    suspend fun getSuperHeroDetail(params : Int): Either<FailureBo, ResultsBo>
}

internal class HeroDetailDomainLayerBridgeImpl(
    private val getSuperHeroDetailUc: DomainLayerContract.Presentation.UseCase<Int, ResultsBo>
) : HeroDetailDomainLayerBridge{

    override suspend fun getSuperHeroDetail(params: Int): Either<FailureBo, ResultsBo> =
        getSuperHeroDetailUc.run(params)

}