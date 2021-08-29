package com.hsofiamunoz.deudoresapp2

import android.app.Application
import androidx.room.Room
import com.hsofiamunoz.deudoresapp2.data.DebtorDataBase
import com.hsofiamunoz.deudoresapp2.data.UserDataBase

class DeudoresApp2 : Application() {

    companion object {
        lateinit var database : DebtorDataBase
        lateinit var databaseuser : UserDataBase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,DebtorDataBase::class.java,"debtor_db").allowMainThreadQueries().build()
        databaseuser = Room.databaseBuilder(this,UserDataBase::class.java,"user_db").allowMainThreadQueries().build()
    }
}