package com.walcker.flickly.core.data.firebase

import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

actual class PlatformFirebaseStorageService : FirebaseStorageService {

    private val storage by lazy { FirebaseStorage.getInstance() }

    actual override suspend fun uploadFile(
        path: String,
        data: ByteArray,
        onProgress: ((progress: Double) -> Unit)?
    ): Result<String> {
        return try {
            val reference = storage.reference.child(path)
            val uploadTask = reference.putBytes(data)

            onProgress?.let { callback ->
                uploadTask.addOnProgressListener { taskSnapshot ->
                    val progress = (100.0 * taskSnapshot.bytesTransferred) / taskSnapshot.totalByteCount
                    callback(progress / 100.0)
                }
            }

            uploadTask.await()
            val downloadUrl = reference.downloadUrl.await().toString()
            Result.success(downloadUrl)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    actual override suspend fun downloadFile(path: String): Result<ByteArray> {
        return try {
            val reference = storage.reference.child(path)
            val data = reference.getBytes(Long.MAX_VALUE).await()
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    actual override suspend fun getDownloadUrl(path: String): Result<String> {
        return try {
            val reference = storage.reference.child(path)
            val url = reference.downloadUrl.await().toString()
            Result.success(url)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    actual override suspend fun deleteFile(path: String): Result<Unit> {
        return try {
            val reference = storage.reference.child(path)
            reference.delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    actual override suspend fun listFiles(path: String): Result<List<String>> {
        return try {
            val reference = storage.reference.child(path)
            val listResult = reference.listAll().await()
            val fileNames = listResult.items.map { it.name }
            Result.success(fileNames)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}