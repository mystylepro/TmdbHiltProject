package com.app.daggerhiltsapplicationtmdb.di.module

import com.app.daggerhiltsapplicationtmdb.BuildConfig
import com.app.daggerhiltsapplicationtmdb.data.api.ApiHelper
import com.app.daggerhiltsapplicationtmdb.data.api.ApiHelperImpl
import com.app.daggerhiltsapplicationtmdb.data.api.ApiService
import com.app.daggerhiltsapplicationtmdb.data.datasource.PopularActressDataSource
import com.app.daggerhiltsapplicationtmdb.data.datasource.PopularMovieDataSource
import com.app.daggerhiltsapplicationtmdb.data.datasource.PopularTVShowDataSource
import com.app.daggerhiltsapplicationtmdb.data.model.PopularTvShowModel
import com.app.daggerhiltsapplicationtmdb.utils.Testing
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {
    @Testing.BaseUrlKey
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Testing.NormalString
    @Provides
    fun provideAPIKey() =BuildConfig.API_KEY

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
            .baseUrl(provideBaseUrl())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiService? = retrofit.create(ApiService :: class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl) : ApiHelper = apiHelper

}