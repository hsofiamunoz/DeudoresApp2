package com.hsofiamunoz.deudoresapp2.data.dao

import androidx.room.*
import com.hsofiamunoz.deudoresapp2.data.entities.Debtor

@Dao
interface DebtorDao {

    @Insert
    fun createDebtor(debtor:Debtor)

    // Funcion que toma todos los datos de la lista, sentencia con SQL
    @Query("SELECT * FROM table_debtor")
    fun getDebtors(): MutableList<Debtor>

    @Query("SELECT * FROM table_debtor WHERE name LIKE :name")
    fun readDebtor(name:String) : Debtor

    @Delete
    fun deleteDebtor(debtor: Debtor)

    @Update
    fun updateDebtor(debtor: Debtor)


}