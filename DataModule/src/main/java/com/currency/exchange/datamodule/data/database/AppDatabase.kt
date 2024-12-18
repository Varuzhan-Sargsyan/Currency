package com.currency.exchange.datamodule.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.currency.exchange.datamodule.data.model.converter.Converters
import com.currency.exchange.datamodule.data.model.entities.CountryDTO
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.RateDTO

@Database(
    entities = [
        CurrencyDTO::class,
        RateDTO::class,
        CountryDTO::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class) // Register the Converters here
abstract class AppDatabase : RoomDatabase() {
    abstract val daoCurrency: CurrencyDao
    abstract val daoRate: RateDao
    abstract val daoCountry: CountryDao

    companion object {
        const val DATABASE_NAME = "currency_database.db"
    }
}

fun Room.buildAppDatabase(
    context: Context,
) = databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = AppDatabase.DATABASE_NAME
    ).build()