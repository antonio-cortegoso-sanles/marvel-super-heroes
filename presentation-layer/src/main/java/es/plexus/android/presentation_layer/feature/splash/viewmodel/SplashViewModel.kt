package es.plexus.android.presentation_layer.feature.splash.viewmodel

import androidx.lifecycle.viewModelScope
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo
import es.plexus.android.domain_layer.feature.SplashDomainLayerBridge
import es.plexus.android.presentation_layer.base.BaseMvvmViewModel
import es.plexus.android.presentation_layer.base.ScreenState
import es.plexus.android.presentation_layer.feature.splash.ui.state.SplashState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SplashViewModel(bridge: SplashDomainLayerBridge) :
    BaseMvvmViewModel<SplashDomainLayerBridge, SplashState>(bridge = bridge) {

    fun onViewCreated() {
        viewModelScope.launch {
            bridge.getSuperHeroesList().fold({}, ::handleSuccessGet)
        }
    }

    private fun handleSuccess(response: Boolean) {
        _screenState.value = ScreenState.Render(SplashState.GoToList)
    }

    private fun handleSuccessGet(response: SuperHeroesDataBo) {
        if (response.results.isEmpty()) {
            viewModelScope.launch {
                bridge.synchronizeSuperHeroesList().fold(::handleError, ::handleSuccess)
            }
        } else {
            handleSuccess(true)
        }
    }

    private fun handleError(failure: FailureBo) {
        _screenState.value = ScreenState.Render(SplashState.ShowError(failure))
    }

}