package com.currency.exchange.datamodule.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.currency.exchange.datamodule.data.model.entities.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currency: Currency) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencies: List<Currency>)

    @Query("SELECT * FROM currency")
    fun currenciesFlow() : Flow<List<Currency>>
}
