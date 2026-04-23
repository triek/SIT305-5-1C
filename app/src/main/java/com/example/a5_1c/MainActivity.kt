package com.example.a5_1c

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a5_1c.ui.bookmarks.BookmarksFragment
import com.example.a5_1c.ui.home.HomeFragment
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showHome(clearBackStack = false)
        }

        findViewById<MaterialButton>(R.id.homeButton).setOnClickListener {
            showHome(clearBackStack = true)
        }

        findViewById<MaterialButton>(R.id.topBookmarksButton).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, BookmarksFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showHome(clearBackStack: Boolean) {
        if (clearBackStack) {
            supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()
    }
}
