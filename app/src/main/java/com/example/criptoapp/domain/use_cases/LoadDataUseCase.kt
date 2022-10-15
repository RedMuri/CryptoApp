package com.example.criptoapp.domain.use_cases

import com.example.criptoapp.domain.repository.Repository

class LoadDataUseCase(private val repository: Repository) {

    operator fun invoke() = repository.loadData()
}