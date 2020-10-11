package com.aurapps.reigntask.model

import com.aurapps.reigntask.model.story.Story

data class WebResponse(
    val hits: List<Story>
)