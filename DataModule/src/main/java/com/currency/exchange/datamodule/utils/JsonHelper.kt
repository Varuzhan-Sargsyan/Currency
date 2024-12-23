package com.currency.exchange.datamodule.utils

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object JsonHelper {
    private val gson = GsonBuilder()
        .serializeNulls()
        .create()

    fun <T> toJson(obj: T?): String {
        return gson.toJson(obj)
    }
    
    fun <T> fromJson(json: String?, type: Class<T>): T? {
        return json?.let { gson.fromJson(json, type) } ?: null as T?
    }
    
    fun <T> fromJsonList(json: String?, type: Class<T>?) : List<T> {
        val typeOfT = TypeToken.getParameterized(MutableList::class.java, type).type
        return gson.fromJson(json, typeOfT)
    }
}
