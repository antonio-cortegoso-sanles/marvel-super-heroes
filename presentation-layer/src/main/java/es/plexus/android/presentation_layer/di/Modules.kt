package es.plexus.android.presentation_layer.di

import es.plexus.android.domain_layer.feature.HEROES_LIST_BRIDGE_TAG
import es.plexus.android.domain_layer.feature.HERO_DETAIL_BRIDGE_TAG
import es.plexus.android.domain_layer.feature.SPLASH_BRIDGE_TAG
import es.plexus.android.presentation_layer.feature.heroes.detail.viewmodel.HeroDetailViewModel
import es.plexus.android.presentation_layer.feature.heroes.list.viewmodel.HeroesListViewModel
import es.plexus.android.presentation_layer.feature.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val splash = module {
    viewModel { SplashViewModel(
        bridge = get(named(name= SPLASH_BRIDGE_TAG))) }
}

@ExperimentalCoroutinesApi
val heroesList = module {
    viewModel { HeroesListViewModel(bridge = get(named(name= HEROES_LIST_BRIDGE_TAG))) }
}

@ExperimentalCoroutinesApi
val heroDetail = module {
    viewModel { HeroDetailViewModel(bridge = get(named(name= HERO_DETAIL_BRIDGE_TAG))) }
}

@ExperimentalCoroutinesApi
val presentationLayerModule = listOf(
    splash, heroesList, heroDetail
)
