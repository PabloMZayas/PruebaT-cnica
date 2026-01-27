package com.veracruzensei.technicalmorty.data.remote

import com.veracruzensei.technicalmorty.data.remote.response.CharacterResponse
import com.veracruzensei.technicalmorty.ui.characters.CharacterModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {

    suspend fun getSingleCharacter(id: String): CharacterResponse {
        return client.get("/api/character/$id").body()
    }

    suspend fun getAllCharacters(): List<CharacterResponse> {
        return listOf(
            CharacterResponse(
                name = "Rick",
                status = "alive"
            ),
            CharacterResponse(
                name = "Rick",
                status = "alive"
            ),
            CharacterResponse(
                name = "Rick",
                status = "alive"
            ),
        )
    }
}

fun CharacterResponse.toDomain(): CharacterModel = CharacterModel(
    id = this.id,
    status = this.status,
    image = this.image,
    name = this.name,
    origin = this.origin,
    location = this.location,
    gender = this.gender,
    species = this.species
)
