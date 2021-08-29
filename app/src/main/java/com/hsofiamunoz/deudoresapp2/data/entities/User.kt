package com.hsofiamunoz.deudoresapp2.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// USer es una clase data class que consta de un Id, correo, contraseña y verificación de la contraseña
@Entity(tableName = "table_user")

data class User (
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "email") val email : String,
    @ColumnInfo(name = "password") val password: String
): Serializable
