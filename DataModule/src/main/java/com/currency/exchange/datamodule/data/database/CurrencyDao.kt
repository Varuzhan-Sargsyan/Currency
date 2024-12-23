package com.currency.exchange.datamodule.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(currencies: List<CurrencyDTO>)

    @Query("SELECT * FROM currencies_dto")
    fun currenciesFlow() : Flow<List<CurrencyDTO>>

    @Query("SELECT * FROM currencies_dto")
    fun currencies() : List<CurrencyDTO>

}
