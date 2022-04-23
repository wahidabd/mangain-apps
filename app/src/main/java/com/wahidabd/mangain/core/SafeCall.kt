package com.wahidabd.mangain.core

import com.wahidabd.mangain.core.ErrorMessage.TIMEOUT_ERROR
import com.wahidabd.mangain.core.ErrorMessage.UNKNOWN_ERROR
import okhttp3.ResponseBody
import retrofit2.Response
import java.net.SocketTimeoutException

class SafeCall {

    //when no request value
    suspend fun <T> enqueue(converter: (ResponseBody) -> GenericErrorResponse?, call: suspend () -> Response<T>): Resource<T> =
        try {
            val res = call()
            val body = res.body()
            val errorBody = res.errorBody()

            if (res.isSuccessful && body != null){
                Resource.success(body)
            }else if (errorBody != null){
                val parsedError = converter(errorBody)
                if (parsedError != null){
                    Resource.error(parsedError.message ?: UNKNOWN_ERROR, null)
                }else{
                    Resource.error(UNKNOWN_ERROR, null)
                }
            }else{
                Resource.error(UNKNOWN_ERROR, null)
            }
        }catch (e: Exception){
            when(e){
                is SocketTimeoutException -> Resource.error(TIMEOUT_ERROR, null)
                else -> Resource.error(UNKNOWN_ERROR, null)
            }
        }

    //when one request value
    suspend fun <T, U> enqueue(req: T, converter: (ResponseBody) -> GenericErrorResponse?, call: suspend (T) -> Response<U>): Resource<U> =
        try {
            val res = call(req)
            val body = res.body()
            val errorBody = res.errorBody()

            if (res.isSuccessful && body != null){
                Resource.success(body)
            }else if (errorBody != null){
                val parsedError = converter(errorBody)
                if (parsedError != null){
                    Resource.error(parsedError.message ?: UNKNOWN_ERROR, null)
                }else{
                    Resource.error(UNKNOWN_ERROR, null)
                }
            }else{
                Resource.error(UNKNOWN_ERROR, null)
            }
        }catch (e: Exception){
            when(e){
                is SocketTimeoutException -> Resource.error(TIMEOUT_ERROR, null)
                else -> Resource.error(UNKNOWN_ERROR, null)
            }
        }

    //when two request value
    suspend fun <T, U, V> enqueue(req1: T, req2: U, converter: (ResponseBody) -> GenericErrorResponse?, call: suspend (T, U) -> Response<V>): Resource<V> =
        try {
            val res = call(req1, req2)
            val body = res.body()
            val errorBody = res.errorBody()

            if (res.isSuccessful && body != null){
                Resource.success(body)
            }else if (errorBody != null){
                val parsedError = converter(errorBody)
                if (parsedError != null){
                    Resource.error(parsedError.message ?: UNKNOWN_ERROR, null)
                }else{
                    Resource.error(UNKNOWN_ERROR, null)
                }
            }else{
                Resource.error(UNKNOWN_ERROR, null)
            }
        }catch (e: Exception){
            when(e){
                is SocketTimeoutException -> Resource.error(TIMEOUT_ERROR, null)
                else -> Resource.error(UNKNOWN_ERROR, null)
            }
        }

}