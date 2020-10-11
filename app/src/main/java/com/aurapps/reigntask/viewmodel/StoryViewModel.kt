package com.aurapps.reigntask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aurapps.reigntask.model.story.StoriesRepository
import com.aurapps.reigntask.model.story.Story
import kotlinx.coroutines.launch
import javax.inject.Inject

class StoryViewModel(app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var repository: StoriesRepository
    private val stories: MutableLiveData<List<Story>> by lazy {
        MutableLiveData<List<Story>>().also {
            loadStories()
        }
    }

    fun reloadStories(){
        loadStories()
    }

    fun getStories(): LiveData<List<Story>> {
        return stories
    }

    private fun loadStories() {
        viewModelScope.launch {
            //Post new values
            val result = repository.getStories(getApplication())
            stories.postValue(result)
            //We save into local storage, in case that user have invalid internet connection
            repository.saveStories(getApplication(), result)
        }
    }

    fun excludeStory(story: Story) {
        viewModelScope.launch {
            repository.excludeStory(getApplication(), story)
        }
    }

}