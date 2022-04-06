package co.kr.sopt_seminar_30th.data.datasource.local

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val FILE_NAME = "SOPTHUB_DATA_STORE"

class SopthubDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = EncryptedSharedPreferences.create(
        FILE_NAME,
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    var userId: String
        set(value) = dataStore.edit { putString("USER_ID", value) }
        get() = dataStore.getString("USER_ID", "") ?: ""
}