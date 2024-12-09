package com.currency.exchange.datamodule.data.model.response

sealed class Response {
    data class Success(val data: Any) : Response()
    data class Error(val message: String) : Response()
    object Loading : Response()
}