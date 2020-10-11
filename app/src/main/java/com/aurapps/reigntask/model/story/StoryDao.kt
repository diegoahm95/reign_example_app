package com.aurapps.reigntask.model.story

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StoryDao {

    @Query("SELECT * FROM story")
    fun getAll(): List<Story>

    @Insert
    fun insertAll(stories: List<Story>)

    @Query("DELETE FROM story")
    fun nukeTable()

}