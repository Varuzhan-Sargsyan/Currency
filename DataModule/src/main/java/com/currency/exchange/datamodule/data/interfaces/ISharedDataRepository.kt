package com.currency.exchange.datamodule.data.interfaces

import kotlinx.coroutines.flow.Flow

interface ISharedDataRepository {
//    fun <T> toMap(key: String, value: T?)
//    fun <T> fromMap(key: String) : T?
//    fun <T> removeFromMap(key: String) : T?
//    fun <T> toFlowMap(key: String, value: T?)
//    fun <T> fromFlowMap(key: String): T?
//    fun <T> removeFromFlowMap(key: String): T?
    fun <T> update(key: String, value: T?)
//    suspend fun <T> subscribe(key: String, block: (T?) -> Unit)
    fun <T> flow(key: String, defaultValue: T?) : Flow<T?>
    fun <T> value(key: String) : T?
}
