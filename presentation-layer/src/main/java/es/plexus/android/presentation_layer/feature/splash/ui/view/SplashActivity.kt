package es.plexus.android.presentation_layer.feature.splash.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import es.plexus.android.domain_layer.feature.SplashDomainLayerBridge
import es.plexus.android.presentation_layer.base.BaseMvvmView
import es.plexus.android.presentation_layer.base.ScreenState
import es.plexus.android.presentation_layer.databinding.ActivitySplashBinding
import es.plexus.android.presentation_layer.feature.heroes.list.ui.view.HeroesListActivity
import es.plexus.android.presentation_layer.feature.splash.ui.state.SplashState
import es.plexus.android.presentation_layer.feature.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class SplashActivity : AppCompatActivity(),
    BaseMvvmView<SplashViewModel, SplashDomainLayerBridge, SplashState> {

    override val viewModel: SplashViewModel by viewModel()
    private lateinit var viewBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashBinding.inflate(layoutInflater)
        initModel()
        setContentView(viewBinding.root)
        viewModel.onViewCreated()
    }


    override fun processRenderState(renderState: SplashState) {
        when (renderState) {
            is SplashState.GoToList -> {
                goToListActivity()
            }
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when (screenState) {
                    is ScreenState.Render<SplashState> -> processRenderState(screenState.renderState)
                }
            }
        }
    }

    private fun goToListActivity() {
        startActivity<HeroesListActivity>()
        finish()
    }
}