package com.cattlesoft.cattlemax.module.domain.convertor

import com.currency.exchange.datamodule.data.model.entities.Currency
import com.currency.exchange.datamodule.data.model.responses.Currencies
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class CurrenciesDeserializer : JsonDeserializer<Currencies> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ) = when(json) {
            is JsonObject -> {
                json.keySet().map {
                    Currency(
                        baseCode = it,
                        name = json.get(it).asString
                    )
                }.let {
                    Currencies(it)
                }
            }
            else  -> null
        }
}