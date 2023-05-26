package com.example.appgithubuser2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.appgithubuser2.data.local.FavUser
import com.example.appgithubuser2.data.local.FavUserDao
import com.example.appgithubuser2.data.local.UserDatabase

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var userDao : FavUserDao?
    private var userDB : UserDatabase?

    init{
        userDB = UserDatabase.getDatabase(application)
        userDao = userDB?.favoriteUserDao()
    }

    fun getFavoriteUser(): LiveData<List<FavUser>>? {
        return userDao?.getFavoriteUser()
    }
}