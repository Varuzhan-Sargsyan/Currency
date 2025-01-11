package com.currency.exchange.datamodule.utils

import com.currency.exchange.datamodule.utils.Const.dateFormatter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Const {
    val dateFormatter: ThreadLocal<SimpleDateFormat> = ThreadLocal.withInitial {
        SimpleDateFormat("yyyy-MM-dd", Locale.US) // Use a fixed locale for consistency
    }

    fun now() : Date =
        Calendar.getInstance(Locale.getDefault()).time
}

fun Date.toCurrencyDateString() =
    dateFormatter.get()?.format(this) ?: ""

