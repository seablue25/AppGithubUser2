package com.example.appgithubuser2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appgithubuser2.API.RetrofitClient
import com.example.appgithubuser2.data.local.FavUser
import com.example.appgithubuser2.data.local.FavUserDao
import com.example.appgithubuser2.data.local.UserDatabase
import com.example.appgithubuser2.model.DetailResponseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {
    val user = MutableLiveData<DetailResponseUser>()

    private var userDao : FavUserDao?
    private var userDB : UserDatabase?

    init{
        userDB = UserDatabase.getDatabase(application)
        userDao = userDB?.favoriteUserDao()
    }

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailResponseUser> {
                override fun onResponse(
                    call: Call<DetailResponseUser>,
                    response: Response<DetailResponseUser>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailResponseUser>, t: Throwable) {
                    t.message?.let { Log.d("failure", it) }
                }

            })
    }

    fun getUserDetail(): LiveData<DetailResponseUser> {
        return user
    }

    fun addToFavorite(username: String, id: Int,avatarUrl: String){
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavUser(
                username,
                id,
                avatarUrl
            )
            userDao?.addToFavorite(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
           userDao?.removeFromFavorite(id)
    }
    }
}