package es.plexus.android.data_layer.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.plexus.android.domain_layer.DomainLayerContract
import es.plexus.android.domain_layer.DomainLayerContract.Data.Companion.SUPER_HEROES_REPOSITORY_TAG
import es.plexus.android.data_layer.contract.DataLayerContract
import es.plexus.android.data_layer.contract.DataLayerContract.SuperHeroesDataSource.Companion.API_BASE_URL
import es.plexus.android.data_layer.contract.DataLayerContract.SuperHeroesDataSource.Companion.API_DATA_SOURCE_TAG
import es.plexus.android.data_layer.contract.DataLayerContract.SuperHeroesDataSource.Companion.API_SERVICE_TAG
import es.plexus.android.data_layer.datasource.SuperHeroesLocalDataSource
import es.plexus.android.data_layer.datasource.SuperHeroesRemoteDataSource
import es.plexus.android.data_layer.repository.SuperHeroesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//datasource
val dataSourceModule = module {
    factory<DataLayerContract.SuperHeroesDataSource.Remote> {
        SuperHeroesRemoteDataSource(apiClient = get(named(API_SERVICE_TAG)))
    }
    factory<DataLayerContract.SuperHeroesDataSource.Local> {
        SuperHeroesLocalDataSource()
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

@ExperimentalCoroutinesApi
val dataLayerModule = listOf(networkModule, dataSourceModule,repositoryModule)

