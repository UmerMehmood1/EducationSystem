package com.appsqueeze.springclient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appsqueeze.springclient.model.Student
import com.appsqueeze.springclient.reotrfit.RetrofitService
import com.appsqueeze.springclient.reotrfit.StudentApi
import com.genuinecoder.springclient.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val retrofitService = RetrofitService()
    private val apiService: StudentApi = retrofitService.retrofit.create(StudentApi::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        if (getLoginState()) {
            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
            finish()
        } else {
            setListeners()

        }
    }

    private fun setListeners() {
        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            if (username.isEmpty() || password.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    "Please Enter username or password",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            val call: Call<MutableList<Student>>? = apiService.allStudents
            var isCredentialCorrect = false
            call!!.enqueue(object : Callback<MutableList<Student>> {
                override fun onResponse(
                    p0: Call<MutableList<Student>>,
                    p1: Response<MutableList<Student>>
                ) {
                    if (p0.isExecuted) {
                        val list = p1.body()
                        var i = 0
                        while (i < (list!!.size)) {
                            val students = list[i]
                            i++
                            isCredentialCorrect =
                                (username == students.username && password == students.password)
                        }
                        if (isCredentialCorrect) {
                            Snackbar.make(binding.root, "Logging in", Snackbar.LENGTH_LONG).show()
                            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                            saveLoginState(true)
                            finish()

                        } else {
                            Snackbar.make(binding.root, "Invalid Credentials", Snackbar.LENGTH_LONG)
                                .show()
                            saveLoginState(false)
                        }
                    }
                }

                override fun onFailure(p0: Call<MutableList<Student>>, p1: Throwable) {
                    Snackbar.make(binding.root, "Server is down", Snackbar.LENGTH_LONG).show()
                    saveLoginState(false)
                }

            })

        }
    }

    private fun getLoginState(): Boolean {
        return this.getSharedPreferences("app", MODE_PRIVATE).getBoolean("haveLogin", false)
    }

    private fun saveLoginState(state: Boolean) {
        this.getSharedPreferences("app", MODE_PRIVATE).edit().putBoolean("haveLogin", state).apply()
    }
}