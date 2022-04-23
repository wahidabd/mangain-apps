package com.wahidabd.mangain.core

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorParser @Inject constructor(
    @BaseRetrofitClient private val retrofit: Retrofit
) {

    fun converterErrorGeneric(error: ResponseBody): GenericErrorResponse? {
        val errorConverter: Converter<ResponseBody, GenericErrorResponse> = retrofit
            .responseBodyConverter(GenericErrorResponse::class.java, arrayOfNulls<Annotation>(0))
        return errorConverter.convert(error)
    }

}