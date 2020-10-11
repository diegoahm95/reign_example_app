package com.aurapps.reigntask.model.story

import com.aurapps.reigntask.app.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoriesRepository @Inject constructor(
    private val remoteSource: StoriesRemoteDataSource,
    private val localSource: StoriesLocalDataSource
) {

    suspend fun getStories(app: App): List<Story> {
        return withContext(Dispatchers.IO){
            val remote = remoteSource.getStories()
            if (remote.isNotEmpty()){
                filterStories(app, remote)
            } else {
                filterStories(app, localSource.getStories(app.appDatabase))
            }
        }
    }

    private fun filterStories(app: App, stories: List<Story>): List<Story> {
        val excluded = getDeletedStories(app)
        val filteredList = stories.toMutableList()
        excluded.forEach {
            val deleted = stories.find { story -> story.id == it.objectId }
            filteredList.remove(deleted)
        }
        return filteredList
    }

    suspend fun saveStories(app: App, stories: List<Story>){
        withContext(Dispatchers.IO){
            app.appDatabase.storyDao().nukeTable()
            app.appDatabase.storyDao().insertAll(stories)
        }
    }

    private fun getDeletedStories(app: App): List<DeletedStory> {
        return localSource.getExcludedStories(app.appDatabase)
    }

    suspend fun excludeStory(app: App, story: Story){
        withContext(Dispatchers.IO){
            app.appDatabase.deletedStoryDao().insert(DeletedStory(objectId = story.id))
        }
    }

}