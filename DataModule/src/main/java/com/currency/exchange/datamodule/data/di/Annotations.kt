package com.currency.exchange.datamodule.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CurrencyRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CountryRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CurrencyOkHttp

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CountryOkHttp
