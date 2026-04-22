package com.example.a5_1c.data

data class Story(
    val id: Int,
    val title: String,
    val description: String,
    val imageRes: Int,
    val category: String,
    val relatedStoryIds: List<Int>
)
