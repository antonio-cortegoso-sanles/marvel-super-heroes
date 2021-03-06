package es.plexus.android.data_layer.di

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.domain_layer.DomainLayerContract.Data.Companion.SUPER_HEROES_REPOSITORY_TAG
import es.plexus.android.data_layer.contract.DataLayerContract
import es.plexus.android.data_layer.contract.DataLayerContract.SuperHeroesDataSource.Companion.API_BASE_URL
import es.plexus.android.data_layer.contract.DataLayerContract.SuperHeroesDataSource.Companion.API_SERVICE_TAG
import es.plexus.android.data_layer.datasource.SuperHeroesPersistenceDataSource
import es.plexus.android.data_layer.datasource.SuperHeroesRemoteDataSource
import es.plexus.android.data_layer.db.APP_DATABASE_TAG
import es.plexus.android.data_layer.db.DB_NAME
import es.plexus.android.data_layer.db.HeroesDatabase
import es.plexus.android.data_layer.networkmanager.NetworkManager
import es.plexus.android.data_layer.networkmanager.NetworkManager.Companion.NETWORK_MANAGER_TAG
import es.plexus.android.data_layer.networkmanager.NetworkManagerImpl
import es.plexus.android.data_layer.repository.SuperHeroesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//datasource
val dataSourceModule = module {
    factory<DataLayerContract.SuperHeroesDataSource.Remote> {
        SuperHeroesRemoteDataSource(
            apiClient = get(named(API_SERVICE_TAG)),
            networkManager = get(named(NETWORK_MANAGER_TAG))
        )
    }
    factory<DataLayerContract.SuperHeroesDataSource.Persistence> {
        SuperHeroesPersistenceDataSource(database = get(named(name = APP_DATABASE_TAG)))
    }

    factory<NetworkManager>(named(NETWORK_MANAGER_TAG)) {
        NetworkManagerImpl(context = androidContext())
    }
}

//repository
val repositoryModule = module {
    single<DomainLayerContract.Data.SuperHeroesRepository>(named(SUPER_HEROES_REPOSITORY_TAG)) {
        SuperHeroesRepository(
            remoteDataSource = get(),
            persistenceDataSource = get()
        )
    }
}


//http client
val networkModule = module {
    fun httpClient(): OkHttpClient {

        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)

        clientBuilder.readTimeout(30, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    // retrofit
    single(named(name = API_SERVICE_TAG)) {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient())
            .baseUrl(API_BASE_URL)
            .build()
    }
}

//db
val persistenceModule = module {
    single(named(name = APP_DATABASE_TAG)) {
        Room.databaseBuilder(
            androidContext(),
            HeroesDatabase::class.java, DB_NAME
        )
            .build()
    }
}

@ExperimentalCoroutinesApi
val dataLayerModule = listOf(networkModule, dataSourceModule, repositoryModule, persistenceModule)

