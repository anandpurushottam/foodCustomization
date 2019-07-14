package com.example.foodorder.data.source.remote

object ErrorHandler {
    fun handleError(error: Throwable): ErrorDTO {
        val errorDTODto: ErrorDTO
        if (error is GenericNetworkException) {
            errorDTODto = ErrorDTO("${error.localizedMessage} (${error.errorCode()})", error.errorCode())
        } else {
            errorDTODto = ErrorDTO("${error.message} (${ErrorCodes.UNKNOWN_ERROR})", ErrorCodes.UNKNOWN_ERROR)
        }
        return errorDTODto
    }
}

