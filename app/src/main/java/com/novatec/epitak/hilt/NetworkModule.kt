package com.novatec.epitak.hilt

import com.novatec.epitak.BuildConfig
import com.novatec.epitak.util.AppPrefs
import com.novatec.epitak.util.UserPrefs
import com.novatec.remote.ApiService
import com.novatec.remote.ApiServiceFactory
import com.novatec.remote.AuthApi
import com.novatec.remote.MapApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiServiceFactory.makeApiService(BuildConfig.DEBUG, AppPrefs.language)
    }

    @Provides
    fun provideMapApiService(): MapApiService {
        return ApiServiceFactory.makeMapApiService(BuildConfig.DEBUG, AppPrefs.language)
    }


    @Provides
    fun provideAuthorizedApiService(): AuthApi {
        return ApiServiceFactory.makeAuthorizedApiService(BuildConfig.DEBUG,
                                                          UserPrefs.token,
                                                          AppPrefs.language)
    }


}


