package es.plexus.android.presentation_layer.feature.heroes.detail.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.feature.HeroDetailDomainLayerBridge
import es.plexus.android.presentation_layer.R
import es.plexus.android.presentation_layer.base.BaseMvvmView
import es.plexus.android.presentation_layer.base.ScreenState
import es.plexus.android.presentation_layer.databinding.FragmentHeroDetailBinding
import es.plexus.android.presentation_layer.domain.ResultsVo
import es.plexus.android.presentation_layer.feature.common.ui.ErrorDialogFragment
import es.plexus.android.presentation_layer.feature.heroes.detail.ui.state.HeroDetailState
import es.plexus.android.presentation_layer.feature.heroes.detail.viewmodel.HeroDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class HeroDetailFragment : Fragment(),
    BaseMvvmView<HeroDetailViewModel, HeroDetailDomainLayerBridge, HeroDetailState> {

    override val viewModel: HeroDetailViewModel by viewModel()
    private lateinit var viewBinding: FragmentHeroDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHeroDetailBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initModel()
        arguments?.let { args ->
            viewModel.onViewCreated(HeroDetailFragmentArgs.fromBundle(args).id)
        }
    }

    override fun processRenderState(renderState: HeroDetailState) {
        when (renderState) {
            is HeroDetailState.LoadHero -> {
                renderData(renderState.data)
            }
            is HeroDetailState.ShowError -> showError(renderState.failure)
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


            activity?.let {
                Glide.with(it)
                    .load(data.picture)
                    .placeholder(R.drawable.hydra_place_holder)
                    .error(R.drawable.hydra_place_holder)
                    .into(ivHeroPic)
            }
        }
    }

    private fun showError(failure: FailureBo) {
        activity?.supportFragmentManager?.let {
            ErrorDialogFragment({}, failure.message).show(
                it,
                ErrorDialogFragment::javaClass.name
            )
        }
    }
}