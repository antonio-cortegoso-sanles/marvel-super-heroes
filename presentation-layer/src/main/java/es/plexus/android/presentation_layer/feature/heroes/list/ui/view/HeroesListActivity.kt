package es.plexus.android.presentation_layer.feature.heroes.list.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.plexus.android.domain_layer.feature.HeroesListDomainLayerBridge
import es.plexus.android.presentation_layer.base.BaseMvvmView
import es.plexus.android.presentation_layer.base.ScreenState
import es.plexus.android.presentation_layer.databinding.ActivityHeroesListBinding
import es.plexus.android.presentation_layer.domain.SuperHeroesDataVo
import es.plexus.android.presentation_layer.feature.heroes.list.ui.adapter.HeroesListAdapter
import es.plexus.android.presentation_layer.feature.heroes.list.ui.state.HeroesListState
import es.plexus.android.presentation_layer.feature.heroes.list.viewmodel.HeroesListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class HeroesListActivity : AppCompatActivity(),
    BaseMvvmView<HeroesListViewModel, HeroesListDomainLayerBridge, HeroesListState> {

    override val viewModel: HeroesListViewModel by viewModel()
    private lateinit var viewBinding: ActivityHeroesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHeroesListBinding.inflate(layoutInflater)
        initModel()
        setContentView(viewBinding.root)
        viewModel.onViewCreated()
    }

    override fun processRenderState(renderState: HeroesListState) {
        when (renderState) {
            is HeroesListState.LoadHeroes -> {
                renderData(renderState.data)
            }
        }
    }

    private fun renderData(data: SuperHeroesDataVo) {
        with(viewBinding){
            rvHeroesList.adapter = HeroesListAdapter(data.results
            ) { data -> viewModel.onSelectHero(data.id) }
            rvHeroesList.layoutManager =  LinearLayoutManager(this@HeroesListActivity)
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when (screenState) {
                    is ScreenState.Render<HeroesListState> -> processRenderState(screenState.renderState)
                }
            }
        }
    }
}