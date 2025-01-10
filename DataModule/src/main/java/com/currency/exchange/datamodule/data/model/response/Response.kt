package com.currency.exchange.datamodule.data.model.response

sealed class Response {
    data class Success(val data: Any) : Response()
    data class Error(val message: String) : Response()
    object Loading : Response()

    fun isSuccess() = this is Success
    fun isError() = this is Error
    fun isLoading() = this is Loading

    fun message() = if (this is Error) message else null

    inline fun <reified T> toListData() : List<T> =
        when {
            this is Success && data is List<*> -> {
                val result = data
                if (result.all { it is T }) {
                    @Suppress("UNCHECKED_CAST")
                    result as List<T>
                } else emptyList()
            }
            else -> emptyList()
        }

    inline fun <reified T> toData() : T? =
        when {
            this is Success && data is T -> data
            else -> null
        }


}