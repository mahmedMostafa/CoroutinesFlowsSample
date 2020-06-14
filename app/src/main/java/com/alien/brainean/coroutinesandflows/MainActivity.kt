package com.alien.brainean.coroutinesandflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alien.brainean.coroutinesandflows.ui.posts.PostsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment()

    }

    private fun setupFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, PostsFragment())
            .commit()
    }
}
