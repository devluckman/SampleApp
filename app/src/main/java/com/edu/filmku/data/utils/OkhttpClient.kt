package com.edu.filmku.data.utils

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 *
 * Created by Lukmanul Hakim on  26/02/24
 * devs.lukman@gmail.com
 */
object OkhttpClient {

    private const val DEFAULT_MAX_REQUEST = 30
    private const val TIMEOUT = 60
    fun create(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        builder.addInterceptor(interceptor).build()
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = DEFAULT_MAX_REQUEST
        builder.dispatcher(dispatcher)

        return builder.build()

    }
}