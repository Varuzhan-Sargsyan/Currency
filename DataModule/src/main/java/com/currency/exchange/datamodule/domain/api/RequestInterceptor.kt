package com.currency.exchange.datamodule.domain.api

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) : Response {
        val newRequest = chain.request().newBuilder()
//            .addHeader("Content-Type", "application/json")
//            .addHeader("Authorization", "Bearer " + "some token"
            .build()
        return chain.proceed(newRequest)
    }
}