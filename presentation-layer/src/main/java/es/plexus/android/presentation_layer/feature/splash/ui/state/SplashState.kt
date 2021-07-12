package es.plexus.android.presentation_layer.feature.splash.ui.state

import es.plexus.android.presentation_layer.base.BaseState

sealed class SplashState : BaseState(){
    object GoToList : SplashState()
}