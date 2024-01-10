package com.rishi.groww.assignment.starwars.di

import android.content.Context
import androidx.room.Room
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.rishi.groww.assignment.starwars.model.database.StarWarsDao
import com.rishi.groww.assignment.starwars.model.database.StarWarsDatabaseRepository
import com.rishi.groww.assignment.starwars.model.database.StarWarsRoomDatabase
import com.rishi.groww.assignment.starwars.model.network.StarWarsApiService
import com.rishi.groww.assignment.starwars.model.repository.AppRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    private const val BASE_URL = "https://swapi.dev/api/"

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(OkHttpProfilerInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideStarWarsApiService(retrofit: Retrofit):StarWarsApiService{
        return retrofit.create(StarWarsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppRepository(starWarsApiService: StarWarsApiService): AppRepository{
        return AppRepository(starWarsApiService, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideBranchInternationalDao(starWarsRoomDatabase: StarWarsRoomDatabase): StarWarsDao {
        return starWarsRoomDatabase.starWarsDao()
    }

    @Provides
    @Singleton
    fun provideStarWarsDatabase(@ApplicationContext applicationContext: Context): StarWarsRoomDatabase {
        return Room.databaseBuilder(
            applicationContext,
            StarWarsRoomDatabase::class.java,
            "StarWarsDatabase"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideStarWarsDatabaseRepository(starWarsDao: StarWarsDao): StarWarsDatabaseRepository {
        return StarWarsDatabaseRepository(starWarsDao)
    }

}
