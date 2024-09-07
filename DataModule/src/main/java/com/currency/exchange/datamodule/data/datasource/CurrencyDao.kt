package com.currency.exchange.datamodule.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.currency.exchange.datamodule.data.model.entities.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Insert
    fun insert(currency: Currency) : Long

    @Query("SELECT * FROM currency WHERE baseCode = :base ORDER BY id DESC LIMIT 1")
    fun currencyFlowByBase(base: String) : Flow<Currency?>
}
