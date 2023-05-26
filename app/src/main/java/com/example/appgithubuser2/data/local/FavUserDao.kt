package com.example.appgithubuser2.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavUserDao {
    @Insert
    fun addToFavorite(favUser: FavUser)

    @Query("SELECT * FROM favoriteuser")
    fun getFavoriteUser(): LiveData<List<FavUser>>

    @Query("SELECT count(*) FROM favoriteuser WHERE favoriteuser.id = :id")
    fun checkUser(id: Int): Int

    @Query( "DELETE FROM favoriteuser WHERE favoriteuser.id= :id")
    fun removeFromFavorite(id: Int): Int
}