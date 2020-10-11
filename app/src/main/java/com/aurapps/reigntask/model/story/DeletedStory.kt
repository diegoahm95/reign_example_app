package com.aurapps.reigntask.model.story

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeletedStory(
    @PrimaryKey
    @ColumnInfo(name = "object_id")
    val objectId: String
)