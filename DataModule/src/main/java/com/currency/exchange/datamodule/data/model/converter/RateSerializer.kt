package com.cattlesoft.cattlemax.module.domain.convertor

import com.currency.exchange.datamodule.data.model.entities.Rate
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class RateSerializer : JsonSerializer<Rate> {
    override fun serialize(
        src: Rate?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement =
        if (src == null)
            JsonObject().asJsonNull // key and value are null
        else {
            JsonObject().apply {
                addProperty("amount", src.amount)
                addProperty("date", src.date)
                addProperty("base", src.base)
                add("rates", JsonObject().apply {
                    addProperty(src.baseSecond, src.rate)
                })
            }
        }
}