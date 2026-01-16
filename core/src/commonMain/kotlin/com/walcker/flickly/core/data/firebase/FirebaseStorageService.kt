package com.walcker.flickly.core.data.firebase

interface FirebaseStorageService {
    suspend fun uploadFile(
        path: String,
        data: ByteArray,
        onProgress: ((progress: Double) -> Unit)? = null
    ): Result<String>

    suspend fun downloadFile(path: String): Result<ByteArray>

    suspend fun getDownloadUrl(path: String): Result<String>

    suspend fun deleteFile(path: String): Result<Unit>

    suspend fun listFiles(path: String): Result<List<String>>

    suspend fun listFolders(path: String): Result<List<String>>
}

expect class PlatformFirebaseStorageService() : FirebaseStorageService {
    override suspend fun uploadFile(path: String, data: ByteArray, onProgress: ((Double) -> Unit)?): Result<String>
    override suspend fun downloadFile(path: String): Result<ByteArray>
    override suspend fun getDownloadUrl(path: String): Result<String>
    override suspend fun deleteFile(path: String): Result<Unit>
    override suspend fun listFiles(path: String): Result<List<String>>
    override suspend fun listFolders(path: String): Result<List<String>>
}

class FirebaseStorageServiceImpl : FirebaseStorageService {
    private val platformService = PlatformFirebaseStorageService()

    override suspend fun uploadFile(
        path: String,
        data: ByteArray,
        onProgress: ((progress: Double) -> Unit)?
    ): Result<String> = platformService.uploadFile(path, data, onProgress)

    override suspend fun downloadFile(path: String): Result<ByteArray> =
        platformService.downloadFile(path)

    override suspend fun getDownloadUrl(path: String): Result<String> =
        platformService.getDownloadUrl(path)

    override suspend fun deleteFile(path: String): Result<Unit> =
        platformService.deleteFile(path)

    override suspend fun listFiles(path: String): Result<List<String>> =
        platformService.listFiles(path)

    override suspend fun listFolders(path: String): Result<List<String>> =
        platformService.listFolders(path)
}