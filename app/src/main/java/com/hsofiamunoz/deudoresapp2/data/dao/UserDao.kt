package com.hsofiamunoz.deudoresapp2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.hsofiamunoz.deudoresapp2.data.entities.User

@Dao
interface UserDao {
    @Insert
    fun saveUser(user: User)

}