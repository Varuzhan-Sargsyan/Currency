package com.currency.exchange.datamodule.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.currency.exchange.datamodule.data.model.entities.CountryDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currencies: List<CountryDTO>)

    @Query("SELECT * FROM countries_dto")
    fun countriesFlow() : Flow<List<CountryDTO>>

}
