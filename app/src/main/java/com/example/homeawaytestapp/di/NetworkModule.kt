package com.example.homeawaytestapp.di

import android.util.Log
import com.example.homeawaytestapp.model.api.VenuesApi
import com.example.homeawaytestapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideVenuesApi(retrofit: Retrofit): VenuesApi = retrofit.create(VenuesApi::class.java)

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor{ chain ->
                val request = chain.request()
                Log.d("OkHttp", "REQUEST: ${request.url()}")
                val response = chain.proceed(request)
//                Log.d("OkHttp", "RESPONSE: ${response.body()}")
                response
            }.build()
    }
}