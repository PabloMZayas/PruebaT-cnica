package com.veracruzensei.technicalmorty.domain

class GetSingleCharacter(private val repository: Repository) {
    suspend  fun getSingleCharacter(id: String) {
        val character = repository.getSingleCharacter(id)
    }
}
