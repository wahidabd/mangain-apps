package com.wahidabd.mangain.di

import com.wahidabd.mangain.domain.usecase.KomikindoInteractor
import com.wahidabd.mangain.domain.usecase.KomikindoUseCase
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
}
