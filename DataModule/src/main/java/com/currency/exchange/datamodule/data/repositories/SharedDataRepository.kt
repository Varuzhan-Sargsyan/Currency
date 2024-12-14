package com.currency.exchange.datamodule.data.repositories

import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Suppress("UNCHECKED_CAST")
class SharedDataRepository : ISharedDataRepository {
    private val sharedDataFlowMap = mutableMapOf<String, MutableStateFlow<Any?>>()

    override fun <T> update(key: String, value: T?) {
        if (!sharedDataFlowMap.containsKey(key))
            sharedDataFlowMap[key] = MutableStateFlow(value)
        else
            sharedDataFlowMap[key]!!.tryEmit(value)
    }

    override fun <T> flow(key: String, defaultValue: T?) : Flow<T?> {
        if (!sharedDataFlowMap.containsKey(key))
            sharedDataFlowMap[key] = MutableStateFlow(defaultValue)
        return sharedDataFlowMap[key] as StateFlow<T?>
    }

    override fun <T> value(key: String) =
        sharedDataFlowMap[key]?.value as T?

}