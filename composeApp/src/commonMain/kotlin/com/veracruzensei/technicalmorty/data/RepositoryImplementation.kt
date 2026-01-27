package com.veracruzensei.technicalmorty.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.veracruzensei.technicalmorty.data.remote.ApiService
import com.veracruzensei.technicalmorty.data.remote.paging.CharactersPagingSource
import com.veracruzensei.technicalmorty.data.remote.response.toDomain
import com.veracruzensei.technicalmorty.domain.model.CharacterModel
import com.veracruzensei.technicalmorty.domain.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImplementation(
    private val api: ApiService,
    private val charactersPagingSource: CharactersPagingSource,
): Repository {

    override suspend fun getSingleCharacter(id: Int): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = { charactersPagingSource }).flow
    }

    companion object {
        private const val MAX_ITEMS = 20
        private const val PREFETCH_ITEMS = 5
    }
}