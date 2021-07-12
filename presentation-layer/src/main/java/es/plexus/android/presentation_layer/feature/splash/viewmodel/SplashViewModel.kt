package es.plexus.android.presentation_layer.feature.splash.viewmodel

import androidx.lifecycle.viewModelScope
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.feature.SplashDomainLayerBridge
import es.plexus.android.presentation_layer.base.BaseMvvmViewModel
import es.plexus.android.presentation_layer.base.ScreenState
import es.plexus.android.presentation_layer.feature.splash.ui.state.SplashState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SplashViewModel(bridge: SplashDomainLayerBridge)
    : BaseMvvmViewModel<SplashDomainLayerBridge,SplashState>(bridge = bridge) {

    fun onViewCreated() {
        viewModelScope.launch {
            bridge.fetchSuperHeroesList().fold(::handleError, ::handleSuccessFetch)
        }
    }

    private fun handleSuccessFetch(response : Boolean){
        _screenState.value = ScreenState.Render(SplashState.GoToList)
    }

    private fun handleError(failure : FailureBo){

    }
}