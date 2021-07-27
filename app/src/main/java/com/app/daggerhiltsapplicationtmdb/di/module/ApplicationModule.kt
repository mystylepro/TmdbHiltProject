package com.app.daggerhiltsapplicationtmdb.di.module

import com.app.daggerhiltsapplicationtmdb.BuildConfig
import com.app.daggerhiltsapplicationtmdb.data.api.ApiHelper
import com.app.daggerhiltsapplicationtmdb.data.api.ApiHelperImpl
import com.app.daggerhiltsapplicationtmdb.data.api.ApiService
import com.app.daggerhiltsapplicationtmdb.utils.Testing
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Testing.BaseUrlKey
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Testing.NormalString
    @Provides
    fun provideAPIKey() = BuildConfig.API_KEY

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            /* .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))*/
            .baseUrl(provideBaseUrl())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl) : ApiHelper = apiHelper

}