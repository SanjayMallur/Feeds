package com.smallur.feeds.data.source.remote

import com.smallur.feeds.data.model.Post
import com.smallur.feeds.data.source.PostDataSource
import io.reactivex.Observable

/**
* Created by Sanjay
 * Class to handle server backend calls
* */
class PostRemoteDataSource(private val apiClient: PostApi) : PostDataSource{

    /**
    * Method to retrieve posts from backend
    * */
    override fun posts(): Observable<List<Post>> {
       return this.apiClient.getPosts()
    }
}