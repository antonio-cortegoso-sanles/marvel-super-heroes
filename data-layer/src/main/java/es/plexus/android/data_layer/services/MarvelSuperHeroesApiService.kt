package es.plexus.android.data_layer.services


import es.plexus.android.data_layer.domain.SuperHeroesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelSuperHeroesApiService {

    @GET("characters")
    suspend fun getSuperHeroesList(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int
    ): Response<SuperHeroesDto>

    @GET("characters/{characterId}")
    suspend fun getSuperHeroDetail(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<SuperHeroesDto>
}