package com.walcker.flickly.core.data.firebase

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine

@OptIn(ExperimentalForeignApi::class)
actual class PlatformFirebaseStorageService : FirebaseStorageService {


    actual override suspend fun uploadFile(
        path: String,
        data: ByteArray,
        onProgress: ((progress: Double) -> Unit)?
    ): Result<String> = suspendCancellableCoroutine { continuation ->
    }

    actual override suspend fun downloadFile(path: String): Result<ByteArray> =
        suspendCancellableCoroutine { continuation ->

        }

    actual override suspend fun getDownloadUrl(path: String): Result<String> =
        suspendCancellableCoroutine { continuation ->

        }

    actual override suspend fun deleteFile(path: String): Result<Unit> =
        suspendCancellableCoroutine { continuation ->

        }

    actual override suspend fun listFiles(path: String): Result<List<String>> =
        suspendCancellableCoroutine { continuation ->

        }

    actual override suspend fun listFolders(path: String): Result<List<String>> =
        suspendCancellableCoroutine { continuation ->

        }
}