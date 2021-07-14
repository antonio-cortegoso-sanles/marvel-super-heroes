package es.plexus.android.presentation_layer.feature.splash.ui.state

import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.presentation_layer.base.BaseState

sealed class SplashState : BaseState(){
    object GoToList : SplashState()
    class ShowError(val failure : FailureBo) : SplashState()
}