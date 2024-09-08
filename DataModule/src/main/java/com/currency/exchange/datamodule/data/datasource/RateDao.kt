package com.currency.exchange.datamodule.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.currency.exchange.datamodule.data.model.entities.Rate
import kotlinx.coroutines.flow.Flow

@Dao
interface RateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rate: Rate)

    @Query("SELECT * FROM rate WHERE base = :base1 AND baseSecond = :base2")
    fun subscribe(base1: String, base2: String) : Flow<Rate?>
}
