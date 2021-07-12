package es.plexus.android.presentation_layer.base

import androidx.lifecycle.ViewModel
import es.plexus.android.domain_layer.base.BaseDomainLayerBridge
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@ExperimentalCoroutinesApi
abstract class BaseMvvmViewModel<T : BaseDomainLayerBridge, S : BaseState>(protected val bridge: T) :
    ViewModel() {

    protected val _screenState: MutableStateFlow<ScreenState<S>> by lazy { MutableStateFlow(ScreenState.Idle) }
    val screenState: StateFlow<ScreenState<S>>
        get() {
            return _screenState
        }
}