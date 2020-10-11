package com.aurapps.reigntask.model.story


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Story(
    @PrimaryKey
    @SerializedName("objectID")
    val id: String,
    val author: String,

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    val createdAt: String,

    @ColumnInfo(name = "story_title")
    @SerializedName("story_title")
    val storyTitle: String?,

    val title: String?,

    @ColumnInfo(name = "story_url")
    @SerializedName("story_url")
    val storyUrl: String?
)