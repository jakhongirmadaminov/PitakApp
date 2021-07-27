package com.novatec.remote

import com.novatec.core.EAppType
import com.novatec.remote.model.*
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    @POST("prof/mb/auth")
    suspend fun userLogin(@Body loginReq: LoginRequest): RespFormatter<UserCredentialsModel>

    @POST("prof/mb/reg")
    suspend fun userRegister(@Body user: UserModel): AuthResponse

    @POST("prof/mb/confirm")
    suspend fun smsConfirm(@Body user: UserCredentialsModel): AuthSuccessResponse

    @Multipart
    @POST("attach/image")
    suspend fun uploadPhoto(@Part file: MultipartBody.Part): PhotoUploadResponse

    @GET("force_update/version")
    suspend fun getActiveAppVersions(@Query("appType") appType: String = EAppType.DRIVER.name,
                                     @Query("platformType") platformType: String = "ANDROID"): RespFormatter<List<IdVersionDTO>>

}

