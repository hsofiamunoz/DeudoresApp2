package com.hsofiamunoz.deudoresapp2

import android.app.Application
import androidx.room.Room
import com.hsofiamunoz.deudoresapp2.data.DebtorDataBase

class DeudoresApp2 : Application() {

    companion object {
        lateinit var database : DebtorDataBase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,DebtorDataBase::class.java,"debtor_db").allowMainThreadQueries().build()
    }
}