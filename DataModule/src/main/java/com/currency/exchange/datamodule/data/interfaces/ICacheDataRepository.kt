package com.currency.exchange.datamodule.data.interfaces

import kotlinx.coroutines.flow.Flow

interface ICacheDataRepository {
    fun <T> update(key: String, value: T?)
    fun <T> flow(key: String, defaultValue: T?) : Flow<T?>
    fun <T> value(key: String) : T?
}
