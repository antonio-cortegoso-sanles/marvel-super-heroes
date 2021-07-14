package es.plexus.android.presentation_layer.feature.heroes.list.viewmodel

import androidx.lifecycle.viewModelScope
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo
import es.plexus.android.domain_layer.feature.HeroesListDomainLayerBridge
import es.plexus.android.presentation_layer.base.BaseMvvmViewModel
import es.plexus.android.presentation_layer.base.ScreenState
import es.plexus.android.presentation_layer.domain.toVo
import es.plexus.android.presentation_layer.feature.heroes.list.ui.state.HeroesListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HeroesListViewModel(
    bridge: HeroesListDomainLayerBridge
) : BaseMvvmViewModel<HeroesListDomainLayerBridge, HeroesListState>(bridge = bridge) {

    fun onViewCreated() {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            bridge.getSuperHeroesList().fold(::handleError, ::handleListSuccess)
        }
    }

    fun onSelectHero(id: Int) {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            bridge.getSuperHeroDetail(id)
                .fold({ handleDetailError(id) }, { handleDetailSuccess(it.id) })
        }
    }

    private fun handleListSuccess(response: SuperHeroesDataBo) {
        _screenState.value = ScreenState.Render(HeroesListState.LoadHeroes(response.toVo()))
    }

    private fun handleDetailSuccess(response: Int) {
        _screenState.value = ScreenState.Render(HeroesListState.GoToDetail(response))
    }

    private fun handleDetailError(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            bridge.fetchSuperHeroDetail(id).fold(::handleError, ::handleDetailSuccess)
        }
    }

    private fun handleError(failure: FailureBo) {
        _screenState.value = ScreenState.Render(HeroesListState.ShowError(failure))
    }
}