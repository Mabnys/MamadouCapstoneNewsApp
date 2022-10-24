package com.mamadou.newsapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.mamadou.newsapp.networking.NetworkStatusChecker
import com.mamadou.newsapp.networking.RemoteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RemoteApiSServiceModule {
    const val BASE_URL ="https://newsapi.org"
    @Singleton
    @Provides
    fun provideRemoteApiService(): RemoteApiService {
        val client: OkHttpClient =
            OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(RemoteApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkStatusChecker(@ApplicationContext context: Context): NetworkStatusChecker {
        return NetworkStatusChecker(context.getSystemService(ConnectivityManager::class.java))
    }
}