package com.currency.exchange.datamodule.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.RateDTO

@Database(
    entities = [
        CurrencyDTO::class,
        RateDTO::class,
    ],
    version = 1,
    exportSchema = true
)
//@TypeConverters(
//    CurrencyListConverter::class
//)
abstract class AppDatabase : RoomDatabase() {
    abstract val daoCurrency: CurrencyDao
    abstract val daoRate: RateDao

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