package es.plexus.android.presentation_layer.feature.heroes.list.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.feature.HeroesListDomainLayerBridge
import es.plexus.android.presentation_layer.base.BaseMvvmView
import es.plexus.android.presentation_layer.base.ScreenState
import es.plexus.android.presentation_layer.databinding.FragmentHeroesListBinding
import es.plexus.android.presentation_layer.domain.SuperHeroesDataVo
import es.plexus.android.presentation_layer.feature.common.ui.ErrorDialogFragment
import es.plexus.android.presentation_layer.feature.heroes.list.ui.adapter.HeroesListAdapter
import es.plexus.android.presentation_layer.feature.heroes.list.ui.state.HeroesListState
import es.plexus.android.presentation_layer.feature.heroes.list.viewmodel.HeroesListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class HeroesListFragment : Fragment(),
    BaseMvvmView<HeroesListViewModel, HeroesListDomainLayerBridge, HeroesListState> {

    override val viewModel: HeroesListViewModel by viewModel()
    private lateinit var viewBinding: FragmentHeroesListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHeroesListBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initModel()
        viewModel.onViewCreated()
    }

    override fun processRenderState(renderState: HeroesListState) {
        when (renderState) {
            is HeroesListState.LoadHeroes -> {
                renderData(renderState.data)
            }
            is HeroesListState.GoToDetail -> {
                renderState.id?.let { data ->
                    goToDetail(data)
                }
            }
            is HeroesListState.ShowError -> showError(renderState.failure)
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when (screenState) {
                    is ScreenState.Render<HeroesListState> -> processRenderState(screenState.renderState)
                    ScreenState.Loading -> {
                        manageLoading(View.VISIBLE)
                    }
                }
            }
        }
    }

    private fun goToDetail(id: Int) {
        val heroId =
            HeroesListFragmentDirections.actionHeroesListFragmentToHeroDetailFragment(id)
        Navigation.findNavController(requireView()).navigate(heroId)
        viewModel.cleanDetailData()
    }

    private fun renderData(data: SuperHeroesDataVo) {
        with(viewBinding) {
            rvHeroesList.adapter = HeroesListAdapter(
                data.results
            ) { data -> viewModel.onSelectHero(data.id) }
            rvHeroesList.layoutManager = LinearLayoutManager(activity)
            rvHeroesList.visibility = View.VISIBLE
        }
        manageLoading(View.GONE)
    }

    private fun manageLoading(visibilityValue: Int) {
        viewBinding.lavLoading.visibility = visibilityValue
    }

    private fun showError(failure: FailureBo) {
        activity?.supportFragmentManager?.let {
            ErrorDialogFragment({}, failure.message).show(
                it,
                ErrorDialogFragment::javaClass.name
            )
        }
        manageLoading(View.GONE)
    }

}