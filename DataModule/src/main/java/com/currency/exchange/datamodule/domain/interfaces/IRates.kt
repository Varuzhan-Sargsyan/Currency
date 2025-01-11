package com.currency.exchange.datamodule.domain.interfaces

import com.currency.exchange.datamodule.domain.model.Rate
import kotlinx.coroutines.flow.Flow

interface IRates {
    suspend fun ratesFlow(): Flow<List<Rate>>
}