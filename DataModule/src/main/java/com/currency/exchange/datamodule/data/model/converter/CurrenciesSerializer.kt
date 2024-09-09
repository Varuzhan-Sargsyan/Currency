package com.cattlesoft.cattlemax.module.domain.convertor

import com.currency.exchange.datamodule.data.model.responses.Currencies
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class CurrenciesSerializer : JsonSerializer<Currencies> {
    override fun serialize(
        src: Currencies?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement =
        if (src == null || src.all.isEmpty())
            JsonObject().asJsonNull // key and value are null
        else {
            JsonObject().apply {
                src.all.forEach {
                    addProperty(it.baseCode, it.name)
                }
            }
        }
}