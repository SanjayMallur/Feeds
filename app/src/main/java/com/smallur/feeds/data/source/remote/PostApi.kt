package com.smallur.feeds.data.source.remote

import com.smallur.feeds.data.model.Post
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * Interface which provides methods to retrieve results from web services
 */
interface PostApi {

    /**
     * Get the list of the posts from the API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>


}