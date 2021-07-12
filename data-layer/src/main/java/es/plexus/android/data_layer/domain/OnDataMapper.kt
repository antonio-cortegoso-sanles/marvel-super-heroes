package es.plexus.android.data_layer.domain

import es.plexus.android.domain_layer.domain.FailureBo

fun FailureDto.dtoToBoFailure(): FailureBo = when (this) {
    FailureDto.Unauthorized -> FailureBo.Unauthorized
    FailureDto.NoNetwork -> FailureBo.NoNetwork
    FailureDto.Unknown -> FailureBo.Unknown
    FailureDto.NoData -> FailureBo.NoData
    FailureDto.Request -> FailureBo.Request
    is FailureDto.Exception -> FailureBo.Exception(this.type,this.message)
}