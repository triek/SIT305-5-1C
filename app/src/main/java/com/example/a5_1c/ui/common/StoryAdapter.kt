package com.example.a5_1c.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a5_1c.R
import com.example.a5_1c.data.Story

class StoryAdapter(
    private val layoutRes: Int,
    stories: List<Story>,
    private val onClick: (Story) -> Unit
) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    private val items = stories.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }

    override fun getItemCount(): Int = items.size

    fun submitList(stories: List<Story>) {
        items.clear()
        items.addAll(stories)
        notifyDataSetChanged()
    }

    class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.storyImage)
        private val title: TextView = itemView.findViewById(R.id.storyTitle)
        private val description: TextView? = itemView.findViewById(R.id.storyDescription)

        fun bind(story: Story, onClick: (Story) -> Unit) {
            image.setImageResource(story.imageRes)
            title.text = story.title
            description?.text = story.description
            itemView.setOnClickListener { onClick(story) }
        }
    }
}
