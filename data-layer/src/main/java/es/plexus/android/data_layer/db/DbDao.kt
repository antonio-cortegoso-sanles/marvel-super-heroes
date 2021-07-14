package es.plexus.android.data_layer.db

import androidx.room.*

@Dao
interface HeroesDao {

    @Insert
    suspend fun insertHero(hero: HeroEntity)

    @Insert
    suspend fun insertHeroDetail(heroDetail: HeroDetailEntity)

    @Update
    suspend fun updateHero(vararg hero: HeroEntity)

    @Update
    suspend fun updateHeroDetail(vararg heroDetail: HeroDetailEntity)

    @Delete
    suspend fun deleteHero(vararg hero: HeroEntity)

    @Delete
    suspend fun deleteHeroDetail(vararg heroDetail: HeroDetailEntity)

    @Query("SELECT * FROM " + HeroEntity.TABLE_NAME)
    suspend fun getHeroesList(): List<HeroEntity>

    @Query("SELECT * FROM " + HeroDetailEntity.TABLE_NAME + " WHERE id = :heroId")
    suspend fun getHeroDetail(heroId: Int): HeroDetailEntity?

}