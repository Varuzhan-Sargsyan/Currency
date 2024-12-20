package com.cattlesoft.cattlemax.module.domain.convertor

//class CurrenciesDeserializer : JsonDeserializer<Currencies> {
//    override fun deserialize(
//        json: JsonElement?,
//        typeOfT: Type?,
//        context: JsonDeserializationContext?
//    ) = when(json) {
//            is JsonObject -> {
//                json.keySet().map {
//                    Currency(
//                        baseCode = it,
//                        name = json.get(it).asString
//                    )
//                }.let {
//                    Currencies(it)
//                }
//            }
//            else  -> null
//        }
//}