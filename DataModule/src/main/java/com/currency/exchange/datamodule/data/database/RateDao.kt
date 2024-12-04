package com.currency.exchange.datamodule.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.currency.exchange.datamodule.data.model.entities.RateDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface RateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rateDTOS: List<RateDTO>)

    @Query("SELECT * FROM rates_dto WHERE currencyFrom = :currencyFrom")
    fun subscribe(currencyFrom: String) : Flow<RateDTO?>

    @Query("DELETE FROM rates_dto WHERE currencyFrom = :currencyFrom")
    fun deleteCurrency(currencyFrom: String)
}
