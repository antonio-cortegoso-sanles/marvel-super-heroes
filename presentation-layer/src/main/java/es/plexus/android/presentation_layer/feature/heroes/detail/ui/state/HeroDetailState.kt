package es.plexus.android.presentation_layer.feature.heroes.detail.ui.state

import es.plexus.android.presentation_layer.base.BaseState
import es.plexus.android.presentation_layer.domain.ResultsVo

sealed class HeroDetailState : BaseState() {

    class LoadHero(val data: ResultsVo) : HeroDetailState()

}