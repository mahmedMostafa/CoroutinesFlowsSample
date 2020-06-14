package com.alien.brainean.coroutinesandflows.ui.posts

import androidx.lifecycle.*
import com.alien.brainean.coroutinesandflows.models.Post
import com.alien.brainean.coroutinesandflows.utils.Event
import com.alien.brainean.coroutinesandflows.utils.onError
import com.alien.brainean.coroutinesandflows.utils.onSuccess
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class PostsViewModel(
    private val repository: PostsRepository
) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val _snackbarMessage = MutableLiveData<Event<String>>()
    val snackbarMessage: LiveData<Event<String>> get() = _snackbarMessage

    private val _progressBar = MutableLiveData<Event<Unit>>()
    val progressBar: LiveData<Event<Unit>> get() = _progressBar


    fun getPostsFirst() = viewModelScope.launch {
        withContext(IO) {
            repository
                .getPosts1()
                .onSuccess {
                    println("Gemy is here")
                    println("Gemy ViewModel data is ${it[0].title}")
                    _posts.postValue(it)
                }
                .onError {
                    println("Gemy is here on error")
                    _snackbarMessage.postValue(Event("Gemy An Error Has Occurred ${it.exception}"))
                }
        }
    }


    val postLiveData1: LiveData<List<Post>> = repository
        .getPostsFlow()
        .onStart { println("flow start") }
        .catch {
            println("Gemy is here on error")
            _snackbarMessage.value = Event("Gemy An Error Has Occurred ${it.message}")
        }
        .asLiveData()

// this is the old way to do it but we just can use asLiveData to do the same
//    val postsLiveData2: LiveData<List<Post>> = liveData {
//        repository.getPostsFlow()
//            .onStart { println("flow start") }
//            .catch { println("flow error") }
//            .collect {
//                emit(it)
//            }
//    }
}

@Suppress("UNCHECKED_CAST")
class PostsViewModelFactory(
    private val postsRepository: PostsRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (PostsViewModel(postsRepository) as T)
}