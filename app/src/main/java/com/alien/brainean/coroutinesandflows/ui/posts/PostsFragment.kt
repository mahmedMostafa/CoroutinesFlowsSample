package com.alien.brainean.coroutinesandflows.ui.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.alien.brainean.coroutinesandflows.R
import com.alien.brainean.coroutinesandflows.utils.EventObserver
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * A simple [Fragment] subclass.
 */
@ExperimentalCoroutinesApi
class PostsFragment : Fragment(R.layout.fragment_posts) {

    private val viewModel by viewModels<PostsViewModel> {
        PostsViewModelFactory(PostsRepository())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
    }


    private fun subscribeToObservers() {
        //viewModel.getPostsFirst()
        viewModel.progressBar.observe(viewLifecycleOwner, EventObserver {
            progress_bar.visibility = View.VISIBLE
        })
        viewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
            progress_bar.visibility = View.GONE
            data_text.text = posts[0].title
            println(posts[0].title)
        })
        viewModel.snackbarMessage.observe(viewLifecycleOwner, EventObserver {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        })

        viewModel.postLiveData1.observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = View.GONE
            data_text.text = it[0].title
            println("Gemy ${it[0].title}")
        })
    }
}
