package com.currency.exchange.datamodule.data.interfaces

import android.content.SharedPreferences
import com.currency.exchange.datamodule.data.interfaces.ISharedPrefs.Companion.KEY_BUY_CURRENCY
import com.currency.exchange.datamodule.data.interfaces.ISharedPrefs.Companion.KEY_SELL_CURRENCY
import com.currency.exchange.datamodule.utils.JsonHelper.fromJson
import com.currency.exchange.datamodule.domain.model.Currency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlin.jvm.java

interface ISharedPrefs {

    companion object {
        const val PREF = "MyAppPrefFile"
        const val KEY_SELL_CURRENCY = "sell_currency"
        const val KEY_BUY_CURRENCY = "buy_currency"
    }

    val sharedPref: SharedPreferences

    fun <T> save(key: String, obj: T?)

    fun remove(key: String)

    fun <V> load(key: String, type: Class<V>) : V?

    fun contains(key: String) : Boolean

}

fun ISharedPrefs.sellCurrency(currency: Currency?) =
    save(KEY_SELL_CURRENCY, currency)

fun ISharedPrefs.buyCurrency(currency: Currency?) =
    save(KEY_BUY_CURRENCY, currency)

fun ISharedPrefs.subscribeToSellCurrency(scope: CoroutineScope) =
    sharedPref
        .getFlowForKey<Currency>(KEY_SELL_CURRENCY)
        .stateIn(scope, SharingStarted.Eagerly, null as Currency?)

fun ISharedPrefs.subscribeToBuyCurrency(scope: CoroutineScope) =
    sharedPref
        .getFlowForKey<Currency>(KEY_BUY_CURRENCY)
        .stateIn(scope, SharingStarted.Eagerly, null as Currency?)

inline fun <reified T> SharedPreferences.getFlowForKey(keyOfObject: String) = callbackFlow {
    val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (keyOfObject == key) {
            trySend(fromJson(getString(key, null), T::class.java))
        }
    }
    registerOnSharedPreferenceChangeListener(listener)
    if (contains(keyOfObject)) {
        send(fromJson(getString(keyOfObject, null), T::class.java))
    }
    awaitClose { unregisterOnSharedPreferenceChangeListener(listener) }
}.buffer(Channel.UNLIMITED) // trySend never fails

inline fun <reified T> ISharedPrefs.flow(keyOfObject: String) =
    sharedPref.getFlowForKey<T>(keyOfObject)
