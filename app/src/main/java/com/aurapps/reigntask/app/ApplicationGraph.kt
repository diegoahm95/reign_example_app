package com.aurapps.reigntask.app

import com.aurapps.reigntask.viewmodel.StoryViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationGraph {

    fun inject(model: StoryViewModel)

}