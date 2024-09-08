package com.currency.exchange.datamodule.usecase

import com.currency.exchange.datamodule.data.model.entities.Rate
import com.currency.exchange.datamodule.data.model.responses.Response
import kotlinx.coroutines.flow.Flow

interface IRateRepository {
    suspend fun downloadRate(baseFrom: String, baseTo: String) : Response
    suspend fun subscribeToRate(baseFrom: String, baseTo: String) : Flow<Rate?>
}