package com.currency.exchange.datamodule.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.currency.exchange.datamodule.data.model.Currency

@Dao
interface CurrencyDao {
    @Insert
    fun insert(currency: Currency) : Long

    @Query("SELECT * FROM currency WHERE baseCode = :base")
    fun currencyByBase(base: String) : Currency?
}
