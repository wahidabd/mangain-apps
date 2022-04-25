package com.wahidabd.mangain.di

import com.wahidabd.mangain.domain.usecase.komik.KomikindoInteractor
import com.wahidabd.mangain.domain.usecase.komik.KomikindoUseCase
import com.wahidabd.mangain.domain.usecase.local.LocalInteractor
import com.wahidabd.mangain.domain.usecase.local.LocalUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {


    @Binds
    @ViewModelScoped
    abstract fun provideKomikindoUseCase(interactor: KomikindoInteractor): KomikindoUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideLocalUseCase(interactor: LocalInteractor): LocalUseCase
}
