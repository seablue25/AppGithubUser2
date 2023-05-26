package com.example.appgithubuser2.API

import com.example.appgithubuser2.model.DetailResponseUser
import com.example.appgithubuser2.model.User
import com.example.appgithubuser2.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface api {
    @GET("search/users")
    @Headers("Authorization: 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")
    fun getSearchUsers(
        @Query("q") query: String
        ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: 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")
    fun getUserDetail(
        @Path("username") username: String
        ):Call<DetailResponseUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: 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")
    fun getFollowers(
        @Path("username") username: String
    ):Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: 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")
    fun getFollowing(
        @Path("username") username: String
    ):Call<ArrayList<User>>
}