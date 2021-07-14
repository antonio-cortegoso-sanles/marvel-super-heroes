package es.plexus.android.domain_layer.feature

import arrow.core.Either
import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.domain_layer.base.BaseDomainLayerBridge
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.ResultsBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo

const val HEROES_LIST_BRIDGE_TAG = "heroesListDomainLayerBridge"

interface HeroesListDomainLayerBridge : BaseDomainLayerBridge {
    suspend fun getSuperHeroesList(): Either<FailureBo, SuperHeroesDataBo>
    suspend fun fetchSuperHeroDetail(params : Int): Either<FailureBo, Int>
    suspend fun getSuperHeroDetail(params : Int): Either<FailureBo, ResultsBo>
}

internal class HeroesListDomainLayerBridgeImpl(
    private val getSuperHeroesListUc: DomainLayerContract.Presentation.UseCase<Nothing, SuperHeroesDataBo>,
    private val fetchSuperHeroDetailUc: DomainLayerContract.Presentation.UseCase<Int, Int>,
    private val getSuperHeroDetailUc: DomainLayerContract.Presentation.UseCase<Int, ResultsBo>
) : HeroesListDomainLayerBridge{

    override suspend fun getSuperHeroesList(): Either<FailureBo, SuperHeroesDataBo> =
        getSuperHeroesListUc.run()

    override suspend fun fetchSuperHeroDetail(params : Int): Either<FailureBo, Int> =
        fetchSuperHeroDetailUc.run(params)

    override suspend fun getSuperHeroDetail(params: Int): Either<FailureBo, ResultsBo> =
        getSuperHeroDetailUc.run(params)

}