package com.walcker.flickly.core.data.firebase

import com.walcker.flickly.core.domain.firebase.FirebaseStorageService
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.storage.storage

internal class FirebaseStorageServiceImpl : FirebaseStorageService {
    private val storage by lazy { Firebase.storage }

    override suspend fun getDownloadUrl(path: String): Result<String> = runCatching {
        storage.reference(path).getDownloadUrl()
    }

    override suspend fun listFiles(path: String): Result<List<String>> = runCatching {
        storage.reference(path).listAll().items.map { it.name }
    }

    override suspend fun listFolders(path: String): Result<List<String>> = runCatching {
        storage.reference(path).listAll().prefixes.map { it.name }
    }
}