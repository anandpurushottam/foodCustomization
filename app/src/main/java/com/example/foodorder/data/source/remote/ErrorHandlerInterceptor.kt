package com.example.foodorder.data.source.remote

import android.content.Context
import com.example.foodorder.util.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONException
import java.net.SocketTimeoutException


class ErrorHandlerInterceptor(application: Context) : Interceptor {
    var context: Context = application;

    override fun intercept(chain: Interceptor.Chain): Response? {


        if (!NetworkUtils.isNetworkAvailable(context)) {
            throw GenericNetworkException(context, ErrorCodes.NO_NETWORK)
        }

        var response: Response? = null

        try {
            response = chain.proceed(chain.request())

            if (response.isSuccessful) {
                return response
            }

            val responseCode = response.code()
            manageException(responseCode)
        } catch (e: Exception) {
            if (e is SocketTimeoutException) {
                manageException(ErrorCodes.NETWORK_TIMEOUT_ERROR)
            } else if (e is JSONException) {
                manageException(ErrorCodes.DATA_FORMAT_MISMATCH)
            } else if (e is GenericNetworkException) {
                manageException(e.errorCode())
            } else {
                manageException(ErrorCodes.UNKNOWN_ERROR)
            }
        }

        return response
    }

    private fun manageException(responseCode: Int) {
        when (responseCode) {
            400 -> throw  GenericNetworkException(context, ErrorCodes.BAD_INPUT)
            401 -> throw GenericNetworkException(context, ErrorCodes.UN_AUTHORIZED)
            404 -> throw GenericNetworkException(context, ErrorCodes.API_NOT_FOUND)
            500 -> throw  GenericNetworkException(context, ErrorCodes.INTERNAL_SERVER_ERROR)

        }
    }

    private fun manageException(errorCodes: ErrorCodes) {

        throw GenericNetworkException(context, errorCodes)
    }


}