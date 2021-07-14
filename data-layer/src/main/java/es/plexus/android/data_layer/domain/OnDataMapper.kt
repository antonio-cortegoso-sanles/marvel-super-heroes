package es.plexus.android.data_layer.domain

import es.plexus.android.domain_layer.domain.FailureBo

fun FailureDto.dtoToBoFailure(): FailureBo = when (this) {
    is FailureDto.Unauthorized -> FailureBo.Unauthorized(this.message,this.code)
    FailureDto.NoData -> FailureBo.NoData(this.message,this.code)
    FailureDto.NoNetwork -> FailureBo.NoNetwork(this.message,this.code)
    is FailureDto.Request -> FailureBo.Request(this.message,this.code)
    FailureDto.Unknown -> FailureBo.Unknown(this.message,this.code)
}