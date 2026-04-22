package com.example.a5_1c.data

import com.example.a5_1c.R

object NewsRepository {
    private val stories = listOf(
        Story(
            id = 1,
            title = "Grand Final Thriller Ends in Penalty Shootout",
            description = "A dramatic final saw both teams level at full-time before a tense penalty shootout decided the champion.",
            imageRes = R.drawable.bg_story_football,
            relatedStoryIds = listOf(2, 3, 4)
        ),
        Story(
            id = 2,
            title = "Rookie Sensation Scores Career-High 38 Points",
            description = "The first-year star delivered a breakout performance to help secure a crucial away victory.",
            imageRes = R.drawable.bg_story_basketball,
            relatedStoryIds = listOf(1, 5, 6)
        ),
        Story(
            id = 3,
            title = "National Team Announces Squad for World Cup",
            description = "The coach has named a balanced squad featuring experienced veterans and emerging young talent.",
            imageRes = R.drawable.bg_story_soccer,
            relatedStoryIds = listOf(1, 7, 8)
        ),
        Story(
            id = 4,
            title = "Top Seed Knocked Out in Stunning Upset",
            description = "One of the tournament favorites was eliminated after an intense five-set battle.",
            imageRes = R.drawable.bg_story_tennis,
            relatedStoryIds = listOf(1, 5, 9)
        ),
        Story(
            id = 5,
            title = "Training Camp Focuses on Defensive Shape",
            description = "Coaches emphasized structured pressing and transition control ahead of the next fixture.",
            imageRes = R.drawable.bg_story_training,
            relatedStoryIds = listOf(2, 4, 6)
        ),
        Story(
            id = 6,
            title = "Stadium Expansion Plans Approved",
            description = "Officials confirmed a multi-phase expansion project to increase capacity and fan experience.",
            imageRes = R.drawable.bg_story_stadium,
            relatedStoryIds = listOf(2, 5, 7)
        ),
        Story(
            id = 7,
            title = "Injury Update: Captain Nearing Full Return",
            description = "Medical staff reported positive progress with the captain expected back in full training soon.",
            imageRes = R.drawable.bg_story_medical,
            relatedStoryIds = listOf(3, 6, 8)
        ),
        Story(
            id = 8,
            title = "Junior League Finals Draw Record Crowd",
            description = "A record turnout highlighted growing grassroots interest and community support.",
            imageRes = R.drawable.bg_story_crowd,
            relatedStoryIds = listOf(3, 7, 9)
        ),
        Story(
            id = 9,
            title = "Coach Praises Team Resilience After Comeback",
            description = "After trailing early, the team responded with composure and tactical discipline.",
            imageRes = R.drawable.bg_story_coach,
            relatedStoryIds = listOf(4, 8, 1)
        )
    )

    fun featuredMatches(): List<Story> = stories.take(4)

    fun latestNews(): List<Story> = stories.drop(1)

    fun storyById(id: Int): Story? = stories.firstOrNull { it.id == id }

    fun relatedStories(story: Story): List<Story> = story.relatedStoryIds.mapNotNull(::storyById)
}
