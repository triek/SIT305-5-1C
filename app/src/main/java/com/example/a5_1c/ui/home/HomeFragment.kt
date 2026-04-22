package com.example.a5_1c.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a5_1c.R
import com.example.a5_1c.data.NewsRepository
import com.example.a5_1c.data.Story
import com.example.a5_1c.ui.common.StoryAdapter
import com.example.a5_1c.ui.detail.DetailFragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val featuredRecycler = view.findViewById<RecyclerView>(R.id.featuredRecyclerView)
        val latestRecycler = view.findViewById<RecyclerView>(R.id.latestRecyclerView)

        featuredRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        featuredRecycler.adapter = StoryAdapter(
            layoutRes = R.layout.item_featured_story,
            stories = NewsRepository.featuredMatches(),
            onClick = ::openDetails
        )

        latestRecycler.layoutManager = LinearLayoutManager(requireContext())
        latestRecycler.adapter = StoryAdapter(
            layoutRes = R.layout.item_news_story,
            stories = NewsRepository.latestNews(),
            onClick = ::openDetails
        )
    }

    private fun openDetails(story: Story) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DetailFragment.newInstance(story.id))
            .addToBackStack(null)
            .commit()
    }
}
