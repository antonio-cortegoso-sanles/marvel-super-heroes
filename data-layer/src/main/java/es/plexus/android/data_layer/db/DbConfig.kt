package es.plexus.android.data_layer.db

import androidx.room.Database
import androidx.room.RoomDatabase

internal const val APP_DATABASE_TAG = "heroesDatabase"
internal const val DB_NAME = "db_heroes.db"

@Database(entities = [HeroEntity::class, HeroDetailEntity::class], version = 1, exportSchema = false)
abstract class HeroesDatabase : RoomDatabase() {
    abstract fun appDao(): HeroesDao
}