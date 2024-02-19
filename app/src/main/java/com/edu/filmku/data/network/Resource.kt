package com.edu.filmku.data.network

import kotlinx.coroutines.flow.Flow

/**
 *
 * Created by Lukmanul Hakim on  15/02/24
 * devs.lukman@gmail.com
 */

typealias SimpleResource = Flow<Resource<Unit>>

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?, message: String?=null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}