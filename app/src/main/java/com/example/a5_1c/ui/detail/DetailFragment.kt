package com.example.a5_1c.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a5_1c.R
import com.example.a5_1c.data.NewsRepository
import com.example.a5_1c.ui.common.StoryAdapter

class DetailFragment : Fragment(R.layout.fragment_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storyId = requireArguments().getInt(ARG_STORY_ID)
        val story = NewsRepository.storyById(storyId) ?: return

        view.findViewById<ImageView>(R.id.detailImage).setImageResource(story.imageRes)
        view.findViewById<TextView>(R.id.detailTitle).text = story.title
        view.findViewById<TextView>(R.id.detailDescription).text = story.description

        val relatedRecycler = view.findViewById<RecyclerView>(R.id.relatedRecyclerView)
        relatedRecycler.layoutManager = LinearLayoutManager(requireContext())
        relatedRecycler.adapter = StoryAdapter(
            layoutRes = R.layout.item_related_story,
            stories = NewsRepository.relatedStories(story),
            onClick = { relatedStory ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, newInstance(relatedStory.id))
                    .addToBackStack(null)
                    .commit()
            }
        )
    }

    companion object {
        private const val ARG_STORY_ID = "story_id"

        fun newInstance(storyId: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_STORY_ID, storyId)
            }
        }
    }
}
