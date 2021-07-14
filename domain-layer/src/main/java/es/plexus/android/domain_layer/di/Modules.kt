package es.plexus.android.domain_layer.di

import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.domain_layer.DomainLayerContract.Data.Companion.SUPER_HEROES_REPOSITORY_TAG
import es.plexus.android.domain_layer.domain.ResultsBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo
import es.plexus.android.domain_layer.feature.*
import es.plexus.android.domain_layer.usecase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val bridgeModule = module {

    factory<SplashDomainLayerBridge>(named(name = SPLASH_BRIDGE_TAG)) {
        SplashDomainLayerBridgeImpl(
            fetchSuperHeroesListUc = get(named(name=FETCH_SUPER_HEROES_LIST_UC_TAG)),
            getSuperHeroesListUc = get(named(name=GET_SUPER_HEROES_LIST_PERSISTED_UC_TAG)),
        )
    }

    factory<HeroesListDomainLayerBridge>(named(name = HEROES_LIST_BRIDGE_TAG)) {
        HeroesListDomainLayerBridgeImpl(
            getSuperHeroesListUc = get(named(name=GET_SUPER_HEROES_LIST_PERSISTED_UC_TAG)),
            fetchSuperHeroDetailUc = get(named(name=FETCH_SUPER_HERO_DETAIL_UC_TAG)),
            getSuperHeroDetailUc = get(named(name=GET_SUPER_HERO_DETAIL_UC_TAG))
        )
    }

    factory<HeroDetailDomainLayerBridge>(named(name = HERO_DETAIL_BRIDGE_TAG)) {
        HeroDetailDomainLayerBridgeImpl(
            getSuperHeroDetailUc = get(named(name=GET_SUPER_HERO_DETAIL_UC_TAG))
        )
    }
}

@ExperimentalCoroutinesApi
val useCaseModule = module {
    factory<DomainLayerContract.Presentation.UseCase<Nothing, Boolean>>(named(name = FETCH_SUPER_HEROES_LIST_UC_TAG)) {
        FetchSuperHeroesListDataUc(superHeroesRepository = get(named(name = SUPER_HEROES_REPOSITORY_TAG)))
    }

    factory<DomainLayerContract.Presentation.UseCase<Int, Int>>(named(name = FETCH_SUPER_HERO_DETAIL_UC_TAG)) {
        FetchSuperHeroDetailUc(superHeroesRepository = get(named(name = SUPER_HEROES_REPOSITORY_TAG)))
    }

    factory<DomainLayerContract.Presentation.UseCase<Nothing, SuperHeroesDataBo>>(named(name = GET_SUPER_HEROES_LIST_PERSISTED_UC_TAG)) {
        GetSuperHeroesListPersistedDataUc(superHeroesRepository = get(named(name = SUPER_HEROES_REPOSITORY_TAG)))
    }

    factory<DomainLayerContract.Presentation.UseCase<Int, ResultsBo>>(named(name = GET_SUPER_HERO_DETAIL_UC_TAG)) {
        GetSuperHeroDetailUc(superHeroesRepository = get(named(name = SUPER_HEROES_REPOSITORY_TAG)))
    }

}

@ExperimentalCoroutinesApi
val domainLayerModule = listOf(bridgeModule, useCaseModule)
