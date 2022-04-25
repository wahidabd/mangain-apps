package com.wahidabd.mangain.di

import android.content.Context
import com.wahidabd.mangain.BuildConfig
import com.wahidabd.mangain.core.BaseRetrofitClient
import com.wahidabd.mangain.core.SafeCall
import com.wahidabd.mangain.data.networks.local.MyDatabase
import com.wahidabd.mangain.data.networks.service.KomikindoService
import com.wahidabd.mangain.data.sources.room.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Room Database
    @Provides
    @Singleton
    fun provideLocalDataSource(db: MyDatabase) = LocalDataSource(db)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = MyDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideBookmarkDao(db: MyDatabase) = db.bookmarkDao()

    @Provides
    @Singleton
    fun provideHistoryDao(db: MyDatabase) = db.historyDao()

    @Provides
    @Singleton
    fun provideSafeCall() = SafeCall()

    @Provides
    @Singleton
    fun provideOkHttpInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

    @BaseRetrofitClient
    @Provides
    @Singleton
    fun provideRetrofitClient(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    @Singleton
    @Provides
    fun provideKomikindoService(@BaseRetrofitClient retrofit: Retrofit): KomikindoService =
        retrofit.create(KomikindoService::class.java)
}