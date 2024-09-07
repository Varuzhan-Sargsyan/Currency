package com.currency.exchange.datamodule.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.currency.exchange.datamodule.data.model.entities.Rate

@Dao
interface RateDao {
    @Insert
    fun insert(rate: Rate) : Long

    @Query("SELECT * FROM rate WHERE currencyId = :currencyId")
    fun ratesByCurrencyId(currencyId: Long) : List<Rate>
}
