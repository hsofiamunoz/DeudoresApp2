package com.hsofiamunoz.deudoresapp2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hsofiamunoz.deudoresapp2.data.dao.UserDao
import com.hsofiamunoz.deudoresapp2.data.entities.User

@Database(entities = [User::class],version = 1)

abstract class UserDataBase:RoomDatabase() {
    abstract fun UserDao(): UserDao
}