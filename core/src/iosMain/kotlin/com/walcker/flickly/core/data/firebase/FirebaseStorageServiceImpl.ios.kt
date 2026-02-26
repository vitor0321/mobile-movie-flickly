package com.walcker.flickly.core.data.firebase

import cocoapods.FirebaseStorage.FIRStorage
import cocoapods.FirebaseStorage.FIRStorageListResult
import cocoapods.FirebaseStorage.FIRStorageReference
import com.walcker.flickly.core.domain.firebase.FirebaseStorageService
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSError
import platform.Foundation.NSURL
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal actual fun createFirebaseStorageService(): FirebaseStorageService = IosFirebaseStorageService()

@OptIn(ExperimentalForeignApi::class)
private class IosFirebaseStorageService : FirebaseStorageService {

    private val storage: FIRStorage = FIRStorage.storage()

    private fun ref(path: String): FIRStorageReference = storage.referenceWithPath(path)

    override suspend fun getDownloadUrl(path: String): Result<String> = runCatching {
        suspendCancellableCoroutine { cont ->
            ref(path).downloadURLWithCompletion { url: NSURL?, error: NSError? ->
                when {
                    error != null -> cont.resumeWithException(Exception(error.localizedDescription))
                    url != null   -> cont.resume(url.absoluteString ?: "")
                    else          -> cont.resumeWithException(Exception("URL nula para $path"))
                }
            }
        }
    }

    override suspend fun listFiles(path: String): Result<List<String>> = runCatching {
        suspendCancellableCoroutine { cont ->
            ref(path).listAllWithCompletion { result: FIRStorageListResult?, error: NSError? ->
                when {
                    error != null  -> cont.resumeWithException(Exception(error.localizedDescription))
                    result != null -> {
                        @Suppress("UNCHECKED_CAST")
                        val items = (result.items() as? List<FIRStorageReference>)
                            ?.map { it.name() } ?: emptyList()
                        cont.resume(items)
                    }
                    else           -> cont.resume(emptyList())
                }
            }
        }
    }

    override suspend fun listFolders(path: String): Result<List<String>> = runCatching {
        suspendCancellableCoroutine { cont ->
            ref(path).listAllWithCompletion { result: FIRStorageListResult?, error: NSError? ->
                when {
                    error != null  -> cont.resumeWithException(Exception(error.localizedDescription))
                    result != null -> {
                        @Suppress("UNCHECKED_CAST")
                        val prefixes = (result.prefixes() as? List<FIRStorageReference>)
                            ?.map { it.name() } ?: emptyList()
                        cont.resume(prefixes)
                    }
                    else           -> cont.resume(emptyList())
                }
            }
        }
    }
}