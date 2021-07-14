package es.plexus.android.data_layer.datasource

import android.content.Context
import arrow.core.Either
import arrow.core.left
import es.plexus.android.data_layer.BuildConfig
import es.plexus.android.data_layer.contract.DataLayerContract
import es.plexus.android.data_layer.domain.FailureDto
import es.plexus.android.data_layer.domain.dtoToBoFailure
import es.plexus.android.data_layer.domain.toBo
import es.plexus.android.data_layer.extensions.safeCall
import es.plexus.android.data_layer.networkmanager.NetworkManager
import es.plexus.android.data_layer.services.MarvelSuperHeroesApiService
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo
import retrofit2.Retrofit
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

class SuperHeroesRemoteDataSource(
    private val apiClient: Retrofit,
    private val context: Context,
    private val networkManager: NetworkManager
) : DataLayerContract.SuperHeroesDataSource.Remote {

    private val publicToken = BuildConfig.PUBLIC_KEY
    private val privateToken = BuildConfig.PRIVATE_KEY
    private val ts = Date().time.toString()

    //private lateinit var xd : NetworkManager

    override suspend fun fetchSuperHeroesListData(): Either<FailureBo, SuperHeroesDataBo> =
        if (networkManager.isNetworkAvailable()) {
            try {
                apiClient.create(MarvelSuperHeroesApiService::class.java)
                    .getSuperHeroesList(ts, publicToken, getHash(ts + privateToken + publicToken))
                    .safeCall({
                        it.toBo()
                    })
            } catch (e: Exception) {
                FailureDto.Unknown.dtoToBoFailure().left()
            }
        } else {
            FailureDto.NoNetwork.dtoToBoFailure().left()
        }

    override suspend fun fetchSuperHeroDetailData(id: Int): Either<FailureBo, SuperHeroesDataBo> =
        if (networkManager.isNetworkAvailable()) {
            try {
                apiClient.create(MarvelSuperHeroesApiService::class.java)
                    .getSuperHeroDetail(
                        id,
                        ts,
                        publicToken,
                        getHash(ts + privateToken + publicToken)
                    )
                    .safeCall({
                        it.toBo()
                    })
            } catch (e: Exception) {
                FailureDto.Unknown.dtoToBoFailure().left()
            }
        } else {
            FailureDto.NoNetwork.dtoToBoFailure().left()
        }

    private fun getHash(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }


}