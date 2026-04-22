package com.example.a5_1c.data

import android.content.Context
import com.example.a5_1c.R

object NewsRepository {
    private const val BOOKMARK_PREFS = "bookmark_prefs"
    private const val BOOKMARK_KEY = "bookmark_story_ids"

    private val stories = listOf(
        Story(
            id = 1,
            title = "Grand Final Thriller Ends in Penalty Shootout",
            description = "A dramatic final saw both teams level at full-time before a tense penalty shootout decided the champion.",
            imageRes = R.drawable.bg_story_football,
            category = "Football",
            relatedStoryIds = listOf(2, 3, 4)
        ),
        Story(
            id = 2,
            title = "Rookie Sensation Scores Career-High 38 Points",
            description = "The first-year star delivered a breakout performance to help secure a crucial away victory.",
            imageRes = R.drawable.bg_story_basketball,
            category = "Basketball",
            relatedStoryIds = listOf(1, 5, 6)
        ),
        Story(
            id = 3,
            title = "National Team Announces Squad for World Cup",
            description = "The coach has named a balanced squad featuring experienced veterans and emerging young talent.",
            imageRes = R.drawable.bg_story_soccer,
            category = "Football",
            relatedStoryIds = listOf(1, 7, 8)
        ),
        Story(
            id = 4,
            title = "Top Seed Knocked Out in Stunning Upset",
            description = "One of the tournament favorites was eliminated after an intense five-set battle.",
            imageRes = R.drawable.bg_story_tennis,
            category = "Cricket",
            relatedStoryIds = listOf(1, 5, 9)
        ),
        Story(
            id = 5,
            title = "Training Camp Focuses on Defensive Shape",
            description = "Coaches emphasized structured pressing and transition control ahead of the next fixture.",
            imageRes = R.drawable.bg_story_training,
            category = "Football",
            relatedStoryIds = listOf(2, 4, 6)
        ),
        Story(
            id = 6,
            title = "Stadium Expansion Plans Approved",
            description = "Officials confirmed a multi-phase expansion project to increase capacity and fan experience.",
            imageRes = R.drawable.bg_story_stadium,
            category = "Cricket",
            relatedStoryIds = listOf(2, 5, 7)
        ),
        Story(
            id = 7,
            title = "Injury Update: Captain Nearing Full Return",
            description = "Medical staff reported positive progress with the captain expected back in full training soon.",
            imageRes = R.drawable.bg_story_medical,
            category = "Basketball",
            relatedStoryIds = listOf(3, 6, 8)
        ),
        Story(
            id = 8,
            title = "Junior League Finals Draw Record Crowd",
            description = "A record turnout highlighted growing grassroots interest and community support.",
            imageRes = R.drawable.bg_story_crowd,
            category = "Cricket",
            relatedStoryIds = listOf(3, 7, 9)
        ),
        Story(
            id = 9,
            title = "Coach Praises Team Resilience After Comeback",
            description = "After trailing early, the team responded with composure and tactical discipline.",
            imageRes = R.drawable.bg_story_coach,
            category = "Basketball",
            relatedStoryIds = listOf(4, 8, 1)
        )
    )

    fun featuredMatches(categoryQuery: String = ""): List<Story> = stories
        .filterByCategory(categoryQuery)
        .take(4)

    fun latestNews(categoryQuery: String = ""): List<Story> = stories
        .drop(1)
        .filterByCategory(categoryQuery)

    fun storyById(id: Int): Story? = stories.firstOrNull { it.id == id }

    fun relatedStories(story: Story): List<Story> = story.relatedStoryIds.mapNotNull(::storyById)

    fun bookmarkedStories(context: Context): List<Story> {
        val bookmarkedIds = bookmarkedStoryIds(context)
        return stories.filter { it.id in bookmarkedIds }
    }

    fun isBookmarked(context: Context, storyId: Int): Boolean = storyId in bookmarkedStoryIds(context)

    fun toggleBookmark(context: Context, storyId: Int): Boolean {
        val bookmarks = bookmarkedStoryIds(context).toMutableSet()
        val nowBookmarked = if (storyId in bookmarks) {
            bookmarks.remove(storyId)
            false
        } else {
            bookmarks.add(storyId)
            true
        }

        context.getSharedPreferences(BOOKMARK_PREFS, Context.MODE_PRIVATE)
            .edit()
            .putStringSet(BOOKMARK_KEY, bookmarks.map { it.toString() }.toSet())
            .apply()

        return nowBookmarked
    }

    private fun bookmarkedStoryIds(context: Context): Set<Int> {
        val raw = context.getSharedPreferences(BOOKMARK_PREFS, Context.MODE_PRIVATE)
            .getStringSet(BOOKMARK_KEY, emptySet())
            .orEmpty()
        return raw.mapNotNull { it.toIntOrNull() }.toSet()
    }

    private fun List<Story>.filterByCategory(query: String): List<Story> {
        if (query.isBlank()) return this
        return filter { it.category.contains(query.trim(), ignoreCase = true) }
    }
}
