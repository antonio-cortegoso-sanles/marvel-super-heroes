package es.plexus.android.presentation_layer.feature.heroes.list.ui.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import es.plexus.android.presentation_layer.R
import es.plexus.android.presentation_layer.databinding.ActivityHeroesBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class HeroesActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityHeroesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHeroesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    override fun onSupportNavigateUp(): Boolean = findNavController( R.id.navHostFragment).navigateUp()

}