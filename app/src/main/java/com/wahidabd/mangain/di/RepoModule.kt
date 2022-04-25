package com.wahidabd.mangain.di

import com.wahidabd.mangain.data.repositories.KomikindoRepository
import com.wahidabd.mangain.data.repositories.LocalRepository
import com.wahidabd.mangain.domain.repository.KomikindoRepositoryImpl
import com.wahidabd.mangain.domain.repository.LocalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [AppModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepoModule {


    @Binds
    abstract fun provideKomikindo(repo: KomikindoRepository): KomikindoRepositoryImpl

    @Binds
    abstract fun provideLocal(repo: LocalRepository): LocalRepositoryImpl

}