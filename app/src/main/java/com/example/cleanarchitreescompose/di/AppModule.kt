package com.example.cleanarchitreescompose.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarchitreescompose.trees_feature.util.Constants.BASE_URL
import com.example.cleanarchitreescompose.di.qualifier.LocalData
import com.example.cleanarchitreescompose.di.qualifier.RemoteData
import com.example.cleanarchitreescompose.trees_feature.data.data_source.TreesDataSource
import com.example.cleanarchitreescompose.trees_feature.data.data_source.local.TreeDao
import com.example.cleanarchitreescompose.trees_feature.data.data_source.local.TreesDatabase
import com.example.cleanarchitreescompose.trees_feature.data.data_source.local.TreesLocalDataSource
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.TreesApi
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.TreesRemoteDataSource
import com.example.cleanarchitreescompose.trees_feature.data.repository.TreesRepositoryImpl
import com.example.cleanarchitreescompose.trees_feature.domain.repository.TreesRepository
import com.example.cleanarchitreescompose.trees_feature.domain.use_case.GetTreesUseCase
import com.example.cleanarchitreescompose.trees_feature.domain.use_case.TreesUseCases
import com.example.cleanarchitreescompose.trees_feature.domain.util.DefaultDispatchers
import com.example.cleanarchitreescompose.trees_feature.domain.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private fun buildOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
//            .addInterceptor(RequestInterceptor())
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = buildOkHttpClient()

    @Singleton
    @Provides
    fun provideTreesRepository(
        localDataSource: TreesLocalDataSource,
        remoteDataSource: TreesRemoteDataSource
    ): TreesRepository = TreesRepositoryImpl(localDataSource, remoteDataSource)

    @Singleton
    @Provides
    @LocalData
    fun provideLocalDataSource(treesDao: TreeDao): TreesDataSource = TreesLocalDataSource(treesDao)

    @Singleton
    @Provides
    @RemoteData
    fun provideRemoteDataSource(treesApi: TreesApi): TreesDataSource = TreesRemoteDataSource(treesApi)

    @Singleton
    @Provides
    fun provideTreesApi(okHttpClient: OkHttpClient): TreesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
            .create(TreesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTreesDatabase(app: Application): TreesDatabase {
        return Room.databaseBuilder(
            app,
            TreesDatabase::class.java,
            TreesDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideTreesDao(database: TreesDatabase): TreeDao = database.treeDao

    @Singleton
    @Provides
    fun provideTreesUseCases(repository: TreesRepository): TreesUseCases {
        return TreesUseCases(
            getTreesUseCase = GetTreesUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideDispatcher(): DispatcherProvider = DefaultDispatchers()
}