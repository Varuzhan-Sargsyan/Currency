package com.currency.exchange.datamodule.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rate", primaryKeys = ["base", "baseSecond"])
data class Rate(

    var amount: Double = 0.0,
    var date: String? = null,

    var base: String = "",

    var baseSecond: String = "",
    var rate: Double = 1.0,

    var createdAt: Long = System.currentTimeMillis()
) {
    companion object {
        fun default() = Rate(amount = 1.0, base = "USD", baseSecond = "GBP", rate = 1.0)
    }
}
