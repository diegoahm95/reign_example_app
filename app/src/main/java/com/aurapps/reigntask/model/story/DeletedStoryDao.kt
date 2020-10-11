package com.aurapps.reigntask.model.story

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DeletedStoryDao {

    @Query("SELECT * FROM deletedStory")
    fun getAll(): List<DeletedStory>

    @Insert
    fun insert(story: DeletedStory)

}