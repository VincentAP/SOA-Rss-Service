package com.nakos.soa_uas_vincentardyanputra_2101658344.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.nakos.soa_uas_vincentardyanputra_2101658344.Constant
import com.nakos.soa_uas_vincentardyanputra_2101658344.data.News24Service
import com.nakos.soa_uas_vincentardyanputra_2101658344.data.NyTimesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector((context)))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .build()

    @News24Retrofit
    @Provides
    @Singleton
    fun provideNews24Retrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constant.NEWS_24_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    @NyTimesRetrofit
    @Provides
    @Singleton
    fun provideNyTimesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constant.NY_TIMES_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNews24Service(
        @News24Retrofit retrofit: Retrofit
    ): News24Service =
        retrofit.create(News24Service::class.java)

    @Provides
    @Singleton
    fun provideNYTimesService(
        @NyTimesRetrofit retrofit: Retrofit
    ): NyTimesService =
        retrofit.create(NyTimesService::class.java)
}