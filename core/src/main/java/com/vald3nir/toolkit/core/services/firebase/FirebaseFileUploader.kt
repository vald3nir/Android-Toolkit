package com.vald3nir.toolkit.core.services.firebase

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object FirebaseFileUploader {

    fun uploadFile(
        data: ByteArray,
        filePath: String,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        val storageRef: StorageReference = FirebaseStorage.getInstance().reference
        val mountainsRef = storageRef.child(filePath)
        mountainsRef.putBytes(data)
            .addOnSuccessListener { onSuccess.invoke() }
            .addOnFailureListener { e -> onError.invoke(e) }
    }

    fun loadFile(
        filePath: String,
        onSuccess: (uri: Uri) -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        FirebaseStorage.getInstance().getReferenceFromUrl(filePath).downloadUrl
            .addOnSuccessListener { onSuccess.invoke(it) }
            .addOnFailureListener { e -> onError.invoke(e) }
    }
}