package com.walcker.flickly.core.data.firebase

import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.walcker.flickly.core.domain.firebase.FirebaseStorageService
import kotlinx.coroutines.tasks.await

internal actual fun createFirebaseStorageService(): FirebaseStorageService = AndroidFirebaseStorageService()

private class AndroidFirebaseStorageService : FirebaseStorageService {

    private val storage by lazy { Firebase.storage }

    override suspend fun getDownloadUrl(path: String): Result<String> = runCatching {
        storage.reference.child(path).downloadUrl.await().toString()
    }

    override suspend fun listFiles(path: String): Result<List<String>> = runCatching {
        storage.reference.child(path).listAll().await().items.map { it.name }
    }

    override suspend fun listFolders(path: String): Result<List<String>> = runCatching {
        storage.reference.child(path).listAll().await().prefixes.map { it.name }
    }
}