package com.example.a5_1c.ui.bookmarks

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a5_1c.R
import com.example.a5_1c.data.NewsRepository
import com.example.a5_1c.ui.common.StoryAdapter
import com.example.a5_1c.ui.detail.DetailFragment

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private lateinit var bookmarkAdapter: StoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.bookmarksRecyclerView)
        val emptyState = view.findViewById<TextView>(R.id.emptyBookmarksText)

        bookmarkAdapter = StoryAdapter(
            layoutRes = R.layout.item_news_story,
            stories = emptyList(),
            onClick = { story ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, DetailFragment.newInstance(story.id))
                    .addToBackStack(null)
                    .commit()
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = bookmarkAdapter

        val bookmarks = NewsRepository.bookmarkedStories(requireContext())
        bookmarkAdapter.submitList(bookmarks)
        emptyState.visibility = if (bookmarks.isEmpty()) View.VISIBLE else View.GONE
    }
}
