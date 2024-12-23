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
    fun insert(rateDTOS: RateDTO)

    @Query("SELECT * FROM rates_dto WHERE base = :code")
    fun rateFlow(code: String) : Flow<RateDTO?>


    @Query("SELECT * FROM rates_dto WHERE base = :code AND date = :date")
    fun rateFlow(code: String, date: String) : Flow<RateDTO?>
}
