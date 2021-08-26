package com.hsofiamunoz.deudoresapp2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hsofiamunoz.deudoresapp2.data.dao.DebtorDao
import com.hsofiamunoz.deudoresapp2.data.entities.Debtor

@Database(entities = [Debtor::class], version = 1)

abstract class DebtorDataBase:RoomDatabase() {

    abstract fun DebtorDao(): DebtorDao

}