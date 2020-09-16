package com.example.bravadive

import androidx.room.*


@Dao
interface SpotIconDao {
    @Query("SELECT * FROM SpotIcon")
    fun getAll(): List<SpotIcon>

    @Query("SELECT * FROM SpotIcon WHERE fkSpotId IN (:spotId)")
    fun loadSpotIconbyId(spotId: Int):List<SpotIcon>

    @Insert
    fun insert(spotIcon: SpotIcon)
    @Insert
    fun insertAll(spotIcons: List<SpotIcon>)
    @Update
    fun update(spotIcon: SpotIcon)
    @Delete
    fun delete(spotIcon: SpotIcon)




}