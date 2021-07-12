package es.plexus.android.marvelsuperheroes.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import es.plexus.android.data_layer.di.dataLayerModule
import es.plexus.android.domain_layer.di.domainLayerModule
import es.plexus.android.presentation_layer.di.presentationLayerModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(presentationLayerModule, domainLayerModule,dataLayerModule).flatten())
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}