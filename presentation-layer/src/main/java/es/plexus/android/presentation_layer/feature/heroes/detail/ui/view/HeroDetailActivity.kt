package es.plexus.android.presentation_layer.feature.heroes.detail.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import es.plexus.android.domain_layer.feature.HeroDetailDomainLayerBridge
import es.plexus.android.presentation_layer.R
import es.plexus.android.presentation_layer.base.BaseMvvmView
import es.plexus.android.presentation_layer.base.ScreenState
import es.plexus.android.presentation_layer.databinding.ActivityHeroDetailBinding
import es.plexus.android.presentation_layer.domain.ResultsVo
import es.plexus.android.presentation_layer.feature.heroes.detail.ui.state.HeroDetailState
import es.plexus.android.presentation_layer.feature.heroes.detail.viewmodel.HeroDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class HeroDetailActivity : AppCompatActivity(),
    BaseMvvmView<HeroDetailViewModel, HeroDetailDomainLayerBridge, HeroDetailState> {

    override val viewModel: HeroDetailViewModel by viewModel()
    private lateinit var viewBinding: ActivityHeroDetailBinding

    companion object {
        const val EXTRA_ID_HERO_KEY = "EXTRA_HERO_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHeroDetailBinding.inflate(layoutInflater)
        initModel()
        setContentView(viewBinding.root)
        viewModel.onViewCreated(intent.getIntExtra(EXTRA_ID_HERO_KEY, 0))
    }


    override fun processRenderState(renderState: HeroDetailState) {
        when (renderState) {
            is HeroDetailState.LoadHero -> {
                renderData(renderState.data)
            }
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when (screenState) {
                    is ScreenState.Render<HeroDetailState> -> processRenderState(screenState.renderState)
                }
            }
        }
    }

    private fun renderData(data: ResultsVo) {

        with(viewBinding) {
            ctlContent.title = data.name

            dataContent.tvHeroIdValue.text = data.id.toString()
            dataContent.tvHeroDescriptionValue.text = data.description
            dataContent.tvHeroSeriesNumberValue.text = data.seriesNumber
            dataContent.tvHeroComicsNumberValue.text = data.comicsNumber
            dataContent.tvHeroStoriesNumberValue.text = data.storiesNumber
            dataContent.tvHeroEventsNumberValue.text = data.storiesNumber

            Glide.with(this@HeroDetailActivity)
                .load(data.picture)
                .placeholder(R.drawable.hydra_place_holder)
                .error(R.drawable.hydra_place_holder)
                .into(ivHeroPic)
        }
    }
}