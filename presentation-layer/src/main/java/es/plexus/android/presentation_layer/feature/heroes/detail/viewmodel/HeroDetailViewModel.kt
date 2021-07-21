package es.plexus.android.presentation_layer.feature.heroes.detail.viewmodel

import androidx.lifecycle.viewModelScope
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.ResultsBo
import es.plexus.android.domain_layer.feature.HeroDetailDomainLayerBridge
import es.plexus.android.presentation_layer.base.BaseMvvmViewModel
import es.plexus.android.presentation_layer.base.ScreenState
import es.plexus.android.presentation_layer.domain.toVo
import es.plexus.android.presentation_layer.feature.heroes.detail.ui.state.HeroDetailState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HeroDetailViewModel(
    bridge: HeroDetailDomainLayerBridge
) : BaseMvvmViewModel<HeroDetailDomainLayerBridge, HeroDetailState>(bridge = bridge) {

    fun onViewCreated(id : Int){
        viewModelScope.launch {
            bridge.getSuperHeroDetail(id).fold(::handleError,::handleSuccess)
        }
    }

    private fun handleSuccess(response : ResultsBo){
        _screenState.value = ScreenState.Render(HeroDetailState.LoadHero(response.toVo()))
    }

    private fun handleError(failure: FailureBo) {
        _screenState.value = ScreenState.Render(HeroDetailState.ShowError(failure))
    }

}