package com.hsofiamunoz.deudoresapp2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hsofiamunoz.deudoresapp2.data.entities.Debtor
import com.hsofiamunoz.deudoresapp2.data.entities.User

@Dao
interface UserDao {
    @Insert
    fun saveUser(user: User)

    @Query("SELECT * FROM table_user WHERE email LIKE :email")
    fun loginUser(email:String) : User


}