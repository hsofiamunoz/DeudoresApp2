package com.hsofiamunoz.deudoresapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.hsofiamunoz.deudoresapp2.data.dao.UserDao
import com.hsofiamunoz.deudoresapp2.data.entities.User
import com.hsofiamunoz.deudoresapp2.databinding.ActivityRegistroBinding
import java.sql.Types.NULL


private const val EMPTY = ""

class RegistroActivity : AppCompatActivity() {

    private lateinit var registroBinnding : ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registroBinnding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(registroBinnding.root)

        with(registroBinnding){
            registerButton.setOnClickListener {

                val intent = Intent(this@RegistroActivity, LoginActivity::class.java)

                val email = emailInputText.text.toString()
                val password = passwordInputText.text.toString()
                val rep_password = repeatPasswordInputText.text.toString()

                val espacios = email.trim()


                if ( espacios == EMPTY)
                {
                    Toast.makeText(this@RegistroActivity,getString(R.string.spaces3), Toast.LENGTH_SHORT).show()

                }
                else
                {   if ( email.isNotEmpty() && password.isNotEmpty() && rep_password.isNotEmpty())
                {
                    if (password == rep_password)
                    {
                        if (password.length >= 6)
                        {
                            saveUser(email,password)
                            repeatPaswwordTextInputLayout.error = null
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                        }
                        else
                            Toast.makeText(this@RegistroActivity, getString(R.string.password_digits), Toast.LENGTH_SHORT).show()
                    }
                    else
                        Toast.makeText(this@RegistroActivity, getString(R.string.password_error), Toast.LENGTH_SHORT).show()
                }
                else
                    Toast.makeText(this@RegistroActivity, getString(R.string.missing_parameters), Toast.LENGTH_SHORT).show()

                }
            }

        }

    }

    private fun saveUser(email: String, password: String) {
        val user = User(id = NULL,email = email, password = password)
        val UserDAO : UserDao = DeudoresApp2.databaseuser.UserDao()
        UserDAO.saveUser(user)
    }
}