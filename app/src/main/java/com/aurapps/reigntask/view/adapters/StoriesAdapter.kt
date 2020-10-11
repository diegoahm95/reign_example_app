package com.aurapps.reigntask.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.aurapps.reigntask.R
import com.aurapps.reigntask.model.story.Story
import com.aurapps.reigntask.view.dialogs.WebViewDialogFragment
import com.aurapps.reigntask.viewmodel.StoryViewModel
import com.chauthai.swipereveallayout.SwipeRevealLayout

class StoriesAdapter(
    private var items: MutableList<Story>, private var viewModel: StoryViewModel,
    private var fragmentManager: FragmentManager
) : RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {

    fun setItems(items: MutableList<Story>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.story_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val context = holder.itemView.context
        holder.title.text = item.title ?: item.storyTitle
        holder.footer.text = context.getString(
            R.string.footer_placeholder, item.author, item.createdAt)

        holder.swipeLayout.setSwipeListener(object : SwipeRevealLayout.SwipeListener {
            override fun onOpened(view: SwipeRevealLayout?) {
                deleteItem(position, item)
            }

            override fun onClosed(view: SwipeRevealLayout?) {
                return
            }

            override fun onSlide(view: SwipeRevealLayout?, slideOffset: Float) {
                return
            }

        })
        holder.swipeLayout.close(false)

        holder.item.setOnClickListener {
            showDetails(item)
        }
    }

    private fun showDetails(item: Story){
        WebViewDialogFragment.newInstance(
            item.storyUrl ?: "https://www.reign.cl/es/")
            .show(fragmentManager, "Details")
    }

    fun deleteItem(position: Int, item: Story){
        items.removeAt(position)
        notifyDataSetChanged()
        viewModel.excludeStory(item)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item: LinearLayout = view.findViewById(R.id.item)
        val title: TextView = view.findViewById(R.id.title)
        val footer: TextView = view.findViewById(R.id.footer)
        val swipeLayout: SwipeRevealLayout = view.findViewById(R.id.swipeLayout)
    }
}