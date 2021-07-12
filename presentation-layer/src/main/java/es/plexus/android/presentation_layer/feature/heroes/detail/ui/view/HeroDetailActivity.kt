package es.plexus.android.presentation_layer.feature.heroes.detail.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.plexus.android.presentation_layer.R

class HeroDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)
    }
}