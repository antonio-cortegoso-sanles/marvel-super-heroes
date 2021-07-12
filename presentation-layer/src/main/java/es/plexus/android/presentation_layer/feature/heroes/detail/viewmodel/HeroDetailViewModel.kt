package es.plexus.android.presentation_layer.feature.heroes.detail.viewmodel

import es.plexus.android.domain_layer.base.BaseDomainLayerBridge
import es.plexus.android.presentation_layer.base.BaseMvvmViewModel
import es.plexus.android.presentation_layer.base.BaseState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HeroDetailViewModel(bridge: BaseDomainLayerBridge
): BaseMvvmViewModel<BaseDomainLayerBridge, BaseState>(bridge = bridge) {

}