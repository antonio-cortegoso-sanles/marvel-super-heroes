package es.plexus.android.presentation_layer.feature.heroes.list.ui.state

import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.presentation_layer.base.BaseState
import es.plexus.android.presentation_layer.domain.SuperHeroesDataVo

sealed class HeroesListState : BaseState() {

    class LoadHeroes(val data: SuperHeroesDataVo) : HeroesListState()
    class GoToDetail(val id: Int? = null) : HeroesListState()
    class ShowError(val failure: FailureBo) : HeroesListState()

}