package com.honogmu.coinmonitor.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.honogmu.coinmonitor.MyApplication

class MyDataStore {
    private val context = MyApplication.getContext()

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_pref")
    }

    private val mDataStore: DataStore<Preferences> = context.dataStore

    private val FLAG_INTRO = booleanPreferencesKey("flag_intro")

    suspend fun updateIntroFlagToTrue() {
        mDataStore.edit { prefs ->
            prefs[FLAG_INTRO] = true
        }
    }

    suspend fun getIntroFlag(): Boolean {
        var introFlag = false
        mDataStore.edit { prefs ->
            introFlag = prefs[FLAG_INTRO] ?: false
        }
        return introFlag
    }
}