package com.currency.exchange.datamodule.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rate")
data class Rate(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var currencyId: Long = 0L,

    var name: String = "",
    var rate: Double = 0.0

)
