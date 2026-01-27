package com.veracruzensei.technicalmorty.data

import com.veracruzensei.technicalmorty.data.remote.ApiService
import com.veracruzensei.technicalmorty.data.remote.response.toDomain
import com.veracruzensei.technicalmorty.domain.model.CharacterModel
import com.veracruzensei.technicalmorty.domain.Repository

class RepositoryImplementation(private val api: ApiService): Repository {
    override suspend fun getSingleCharacter(id: Int): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }
}