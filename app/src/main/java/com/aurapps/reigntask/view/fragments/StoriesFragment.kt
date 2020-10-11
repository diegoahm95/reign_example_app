package com.aurapps.reigntask.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurapps.reigntask.R
import com.aurapps.reigntask.app.App
import com.aurapps.reigntask.model.story.Story
import com.aurapps.reigntask.view.adapters.StoriesAdapter
import com.aurapps.reigntask.viewmodel.StoryViewModel
import kotlinx.android.synthetic.main.fragment_stories.*

class StoriesFragment : Fragment() {

    private lateinit var viewModel: StoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(StoryViewModel::class.java)
        (activity?.applicationContext as App).applicationGraph.inject(viewModel)
        return inflater.inflate(R.layout.fragment_stories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeLayout.setOnRefreshListener {
            viewModel.reloadStories()
        }
        swipeLayout.isRefreshing = true
        fetchData()
    }

    private fun updateList(stories: List<Story>){
        with(list) {
            if (adapter == null){
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
                adapter = StoriesAdapter(
                    stories.toMutableList(), viewModel, parentFragmentManager
                )
            } else {
                (adapter as StoriesAdapter).setItems(stories.toMutableList())
            }
        }
    }

    private fun fetchData(){
        viewModel.getStories().observe(viewLifecycleOwner, Observer<List<Story>>{ stories ->
            swipeLayout.isRefreshing = false
            updateList(stories)
        })
    }

    companion object {
        fun newInstance() = StoriesFragment()
    }
}