package com.walcker.flickly.core.domain.firebase

public interface FirebaseStorageService {
    suspend fun getDownloadUrl(path: String): Result<String>
    suspend fun listFiles(path: String): Result<List<String>>
    suspend fun listFolders(path: String): Result<List<String>>
}