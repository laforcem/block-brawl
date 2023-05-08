package edu.mines.olsgard_a4.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    companion object DataStoreManager {
        private const val DATA_STORE_NAME = "blockbrawl"
        private val Context.dataStore: DataStore<Preferences>
                by preferencesDataStore(name = DATA_STORE_NAME)
    }


    private val MUSIC_KEY = booleanPreferencesKey("music_key")
    private val SFX_KEY = booleanPreferencesKey("sfx_key")

    val sfxDataflow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[SFX_KEY] ?: false
    }


    suspend fun setSfx(newValue: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SFX_KEY] = newValue
        }
    }

    val musicDataflow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[MUSIC_KEY] ?: false
    }


    suspend fun setMusic(newValue: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[MUSIC_KEY] = newValue
        }
    }





}