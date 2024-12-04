package com.cattlesoft.cattlemax.module.domain.convertor

//class RateDeserializer : JsonDeserializer<Rate> {
//    override fun deserialize(
//        json: JsonElement?,
//        typeOfT: Type?,
//        context: JsonDeserializationContext?
//    ) = when(json) {
//            is JsonObject -> {
//                val baseSecond = json.get("rates").asJsonObject.keySet().first()
//                Rate(
//                    amount = json.get("amount").asDouble,
//                    date = json.get("date").asString,
//                    base = json.get("base").asString,
//                    baseSecond = baseSecond,
//                    rate = json.get("rates").asJsonObject.get(baseSecond).asDouble
//                )
//            }
//            else  -> null
//        }
//}