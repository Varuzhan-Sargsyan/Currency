package com.currency.exchange.datamodule.data.model.entities

data class NameDTO(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeNameDTO>? // Nullable for safety
)
