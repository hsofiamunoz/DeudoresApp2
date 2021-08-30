package com.hsofiamunoz.deudoresapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import com.hsofiamunoz.deudoresapp2.data.dao.DebtorDao
import com.hsofiamunoz.deudoresapp2.data.dao.UserDao
import com.hsofiamunoz.deudoresapp2.data.entities.Debtor
import com.hsofiamunoz.deudoresapp2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Variables del intent y Binding
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        val data = intent.extras

        loginBinding.loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

            val email = loginBinding.email1TextInput.text.toString()
            val password = loginBinding.passwordTextInput.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                  searchUser(loginBinding.email1TextInput.text.toString(),
                  loginBinding.passwordTextInput.text.toString())
            }
            else
                Toast.makeText(this,getString(R.string.missing_parameters), Toast.LENGTH_SHORT).show()


        }
        loginBinding.registerLinkTextView.setOnClickListener {
            val intent = Intent(this,RegistroActivity::class.java)
            startActivity(intent)
        }


    }

    private fun searchUser( email: String, password: String){
        // se carga el Dao


    }
}
