package es.plexus.android.data_layer.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.plexus.android.data_layer.domain.FailureDto
import es.plexus.android.data_layer.domain.dtoToBoFailure
import es.plexus.android.domain_layer.domain.FailureBo
import retrofit2.Response

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )
        when {
            networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> true
            networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> true
            // for other devices which are able to connect with Ethernet
            networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true -> true
            // for VPN connections
            networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_VPN) == true -> true
            else -> false
        }
    } else {
        connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo?.isConnected == true
    }
}

fun <T, R> Response<T>.safeCall(
    transform: (T) -> R,
    errorHandler: (Response<T>).() -> Either<FailureBo, Nothing> = { handleDataSourceError() }
): Either<FailureBo, R> =
    try {
        run {
            if (isSuccessful) {
                val body = body()
                if (body != null) {
                    transform(body).right()
                } else {
                    errorHandler()
                }
            } else {
                errorHandler()
            }
        }
    } catch (exception: Exception) {
        Log.e("t", "Error: ${exception.message}")
        errorHandler()
    }

fun <T> Response<T>?.handleDataSourceError(): Either<FailureBo, Nothing> =
    when (this?.code()) {
        in 400..408 -> FailureDto.Unauthorized(this?.message()?:"",this?.code()?:400)
        409 -> FailureDto.Request(this.message(),this.code())
        else -> FailureDto.Unknown
    }.dtoToBoFailure().left()