package com.wahidabd.mangain.viewmodel

import androidx.lifecycle.ViewModel
import com.wahidabd.mangain.domain.usecase.KomikindoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: KomikindoUseCase
): ViewModel() {

    val home = useCase.home().distinctUntilChanged()

}