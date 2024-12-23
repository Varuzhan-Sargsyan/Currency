package com.currency.exchange.datamodule.data.repositories

import android.content.Context
import android.content.SharedPreferences
import com.currency.exchange.datamodule.data.interfaces.ILocalRepository
import com.currency.exchange.datamodule.data.interfaces.ILocalRepository.Companion.PREF
import com.currency.exchange.datamodule.data.interfaces.getFlowForKey
import com.currency.exchange.datamodule.utils.JsonHelper.fromJson
import com.currency.exchange.datamodule.utils.JsonHelper.toJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class LocalRepository(context: Context) : ILocalRepository {

    override val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    override fun <T> save(key: String, obj: T?) {
        val json = toJson(obj)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, json)
        editor.apply()
    }

    override fun remove(key: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(key)
        editor.apply()
    }

    override fun <V> load(key: String, type: Class<V>): V? {
        return fromJson(sharedPref.getString(key, null), type)
    }

    override fun contains(key: String) = sharedPref.contains(key)

    inline fun <reified T> subscribe(
        scope: CoroutineScope,
        key: String,
    ) = sharedPref
        .getFlowForKey<T>(key)
        .stateIn(scope, SharingStarted.Eagerly, null as T?)
}