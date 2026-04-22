package com.example.a5_1c.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a5_1c.R
import com.example.a5_1c.data.NewsRepository
import com.example.a5_1c.data.Story
import com.example.a5_1c.ui.bookmarks.BookmarksFragment
import com.example.a5_1c.ui.common.StoryAdapter
import com.example.a5_1c.ui.detail.DetailFragment
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var featuredAdapter: StoryAdapter
    private lateinit var latestAdapter: StoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val featuredRecycler = view.findViewById<RecyclerView>(R.id.featuredRecyclerView)
        val latestRecycler = view.findViewById<RecyclerView>(R.id.latestRecyclerView)
        val categorySearchView = view.findViewById<SearchView>(R.id.categorySearchView)
        val bookmarksButton = view.findViewById<MaterialButton>(R.id.bookmarksButton)

        featuredAdapter = StoryAdapter(
            layoutRes = R.layout.item_featured_story,
            stories = NewsRepository.featuredMatches(),
            onClick = ::openDetails
        )
        latestAdapter = StoryAdapter(
            layoutRes = R.layout.item_news_story,
            stories = NewsRepository.latestNews(),
            onClick = ::openDetails
        )

        featuredRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        featuredRecycler.adapter = featuredAdapter

        latestRecycler.layoutManager = LinearLayoutManager(requireContext())
        latestRecycler.adapter = latestAdapter

        categorySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                applyCategoryFilter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                applyCategoryFilter(newText)
                return true
            }
        })

        bookmarksButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, BookmarksFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun applyCategoryFilter(query: String?) {
        featuredAdapter.submitList(NewsRepository.featuredMatches(query.orEmpty()))
        latestAdapter.submitList(NewsRepository.latestNews(query.orEmpty()))
    }

    private fun openDetails(story: Story) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DetailFragment.newInstance(story.id))
            .addToBackStack(null)
            .commit()
    }
}
