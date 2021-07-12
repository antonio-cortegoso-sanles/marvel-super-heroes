package es.plexus.android.presentation_layer.base

import es.plexus.android.domain_layer.base.BaseDomainLayerBridge
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
interface BaseMvvmView<T : BaseMvvmViewModel<S, U>, S : BaseDomainLayerBridge, U : BaseState> {

    val viewModel: T

    /**
     * Handles the possible state values
     *
     * @param renderState the actual state, extending from U
     */
    fun processRenderState(renderState: U)

    /**
     * Init viewModel
     */
    fun initModel()

}