package jp.kztproject.rewardedtodo.data.auth

import android.annotation.SuppressLint
import android.content.SharedPreferences
import jp.kztproject.rewardedtodo.common.kvs.EncryptedStore
import javax.inject.Inject
import javax.inject.Named

@SuppressLint("ApplySharedPref")
class TodoistAccessTokenRepository @Inject constructor(
        private val api: TodoistApi,
        @Named("encrypted") private val preferences: SharedPreferences
) : ITodoistAccessTokenRepository {

    override suspend fun refresh(clientId: String, clientToken: String, code: String) {
        val accessToken = api.fetchAccessToken(clientId, clientToken, code).accessToken
        preferences.edit()
                .putString(EncryptedStore.TODOIST_ACCESS_TOKEN, accessToken)
                .commit()
    }

    override suspend fun get(): String {
        return preferences.getString(EncryptedStore.TODOIST_ACCESS_TOKEN, "")!!
    }

    override suspend fun clear() {
        preferences.edit()
                .remove(EncryptedStore.TODOIST_ACCESS_TOKEN)
                .commit()
    }
}