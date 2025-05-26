package com.example.practiceplacement.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import okhttp3.internal.http2.Header

class DataStoreManager(private val context: Context) {

    suspend fun saveAfterConfirm(place: String) {
        context.dataStore.edit { prefs ->
            prefs[PrefKeys.STATUS] = "Подтвержден"
            prefs[PrefKeys.PLACE] = place
        }
    }

    suspend fun saveAfterLogin(id: String, head_teacher: String) {
        context.dataStore.edit { prefs ->
            prefs[PrefKeys.ID] = id
            prefs[PrefKeys.HEAD_TEACHER] = head_teacher
        }
    }

    suspend fun getTeacher(): String {
        val prefs = context.dataStore.data.first()
        return prefs[PrefKeys.HEAD_TEACHER] ?: ""
    }

    suspend fun getStatus(): String {
        val prefs = context.dataStore.data.first()
        return prefs[PrefKeys.STATUS] ?: ""
    }

    suspend fun getPlace(): String {
        val prefs = context.dataStore.data.first()
        return prefs[PrefKeys.PLACE] ?: ""
    }

    suspend fun getId(): String {
        val prefs = context.dataStore.data.first()
        return prefs[PrefKeys.ID] ?: ""
    }
}
