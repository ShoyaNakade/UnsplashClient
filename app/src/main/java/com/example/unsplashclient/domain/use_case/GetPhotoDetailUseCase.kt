package com.example.unsplashclient.domain.use_case

import android.util.Log
import com.example.unsplashclient.common.NetworkResponse
import com.example.unsplashclient.data.remote.toPhotoDetail
import com.example.unsplashclient.domain.model.PhotoDetail
import com.example.unsplashclient.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotoDetailUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    operator fun invoke(photoId: String): Flow<NetworkResponse<PhotoDetail>> = flow {
        Log.d("GetPhotoDetailUseCase", "photoId: $photoId")
        try {
            emit(NetworkResponse.Loading<PhotoDetail>())
            val photoDetail = repository.getPhotoById(photoId).toPhotoDetail()
            Log.d("GetPhotoDetailUseCase", "photoDetail: $photoDetail")
            emit(NetworkResponse.Success<PhotoDetail>(photoDetail))
        } catch (e: Exception) {
            Log.d("GetPhotoDetailUseCase", "e: $e")
            emit(NetworkResponse.Failure<PhotoDetail>(e.localizedMessage ?: "Unknown error"))
        }
    }
}
