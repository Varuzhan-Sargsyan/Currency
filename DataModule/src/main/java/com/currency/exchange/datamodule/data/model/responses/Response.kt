package com.currency.exchange.datamodule.data.model.responses

data class Response(
    var message: String? = null,
    var code: Int? = null,
    
    var isSuccessful: Boolean = code == null || code == 200 || code == 201
)